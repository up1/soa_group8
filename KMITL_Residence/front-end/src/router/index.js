import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Reservation from '@/components/Reservation'
import BlankComponent from '@/components/BlankComponent'
import ConfirmReservation from '@/components/reservation/ConfirmReservation'
import CancelReservation from '@/components/reservation/CancelReservation'
import Administrator from '@/components/Administrator'
import LoginPanel from '@/components/administrator/LoginPanel'

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
          path: '',
          meta: { requiredAuth: true }
        },
        {
          path: 'login',
          component: LoginPanel
        }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some((x) => x.meta.isBlankComponent)) {
      next({path: '/'})
  }
  if(to.matched.some((x) => x.meta.requiredHashId)){
    if(!to.query.id){
      next({path: '/'})
    }
  }
  if(to.matched.some((x) => x.meta.requiredAuth)){
    const _token = VueCookie.get('_token')
    store.dispatch('setUnauthenticated')
    if(_token){
          User.isAuthenticated(_token)
              .then(res => {
                next()
                store.dispatch('setAuthenticated')
                store.dispatch('setUserInfo', res.data)
              })
              .catch(() => {
                next({path: `/administrator/login?redirect=${to.fullPath}`})          
              })
    }else{
      next({path: `/administrator/login?redirect=${to.fullPath}`})
    }
  }
  next()
})

export default router