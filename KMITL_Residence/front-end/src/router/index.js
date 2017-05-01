import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Reservation from '@/components/Reservation'
import BlankComponent from '@/components/BlankComponent'
import ConfirmReservation from '@/components/reservation/ConfirmReservation'
import CancelReservation from '@/components/reservation/CancelReservation'
import Administrator from '@/components/Administrator'
import Dashboard from '@/components/administrator/Dashboard'
import LoginPanel from '@/components/administrator/LoginPanel'
import Customer from '@/components/administrator/customer/CustomerPage'
import Rooms from '@/components/administrator/Rooms'
import NotFound from '@/components/errors/NotFound'

import { User } from '@/services'
import store from '../store'
import VueCookie from 'vue-cookie'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: Home
    },
    {
      path: '/reservation',
      component: Reservation
    },
    {
      path: '/reservation/:reservationId',
      component: BlankComponent,
      children: [
        {
          path: '',
          meta: { isBlankComponent: true },
        },
        {
          path: 'confirm',
          component: ConfirmReservation,
          meta: { requiredHashId: true },
          props: (route) => ({
            reservationId: route.params.reservationId,
            hashId: route.query.id
          })
        },
        {
          path: 'cancel',
          component: CancelReservation,
          meta: { requiredHashId: true },
          props: (route) => ({
            reservationId: route.params.reservationId,
            hashId: route.query.id
          })
        }
      ]
    },
    {
      path: '/administrator',
      component: Administrator,
      children: [
        {
          path: 'dashboard',
          meta: { requiredAuth: true },
          component: Dashboard
        },
        {
          path: 'customer',
          meta: { requiredAuth: true },
          component: Customer,
          children: [
            {
              path: 'checkin'
            },
            {
              path: 'checkout'
            },
            {
              path: 'changeroom'
            }
          ]
        },
        {
          path: 'rooms',
          meta: { requiredAuth: true },
          component: Rooms

        },
        {
          path: 'users/edit/:username',
          meta: { requiredAuth: true, requiredAdminRole: true }
        },
        {
          path: 'login',
          component: LoginPanel,
          meta: { loginPage: true }
        }
      ]
    },
    {
      path: '*',
      component: BlankComponent,
      children: [
        {
          path: '',
          component: NotFound
        }
      ]
    }
  ]
})

const match = (to, from, next) => {
    if (to.matched.some((x) => x.meta.isBlankComponent)) {
        next({path: '/'})
    }
    if(to.matched.some((x) => x.meta.requiredHashId)){
        if(!to.query.id){
        next({path: '/'})
        }
    }

    if(to.matched[0].path === "/administrator" && to.matched.length == 1){
        next({path: '/administrator/dashboard'})
    }

    if(to.matched.some((x) => x.meta.requiredAuth)){
        if(store.getters.getAuthenState){
            next()
        }else{
        next({path: `/administrator/login?redirect=${to.fullPath}`})
        }
    }

    if(to.matched.some(x => x.meta.requiredAdminRole)){
      if(store.getters.getUserInfo.role === "Admin" || store.getters.getUserInfo.username === to.params.username){
        next()
      } else {
        next({ path: '/administrator/dashboard'})
      }
    }

    if(to.matched.some(x => x.meta.loginPage)){
      if(store.getters.getAuthenState){
        next({ path: '/administrator/dashboard' })
      }
    }
    next()
}

router.beforeEach((to, from, next) => {
  const _token = VueCookie.get('_token')
  if(_token){
    User.isAuthenticated(_token)
        .then(res => {
            store.dispatch('setAuthenticated')
            store.dispatch('setUserInfo', res.data)
            match(to, from, next)
        })
        .catch(() => {
            store.dispatch('authenticationReset')
            match(to, from, next)
        })
  }else{
      store.dispatch('authenticationReset')
      match(to, from, next)
  }
})

export default router