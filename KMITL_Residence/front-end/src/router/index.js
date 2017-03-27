import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Reservation from '@/components/Reservation'
import BlankComponent from '@/components/BlankComponent'
import ConfirmReservation from '@/components/reservation/ConfirmReservation'
import CancelReservation from '@/components/reservation/CancelReservation'

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
  next()
})

export default router