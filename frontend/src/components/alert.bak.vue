<template>
  <div>
    <b-alert class="position-fixed w-100" :show="dismissCountDown"
             dismissible
             :variant="variant"
             title="Error"
             @dismissed="dismissCountdown=0"
             @dismiss-count-down="countDownChanged"
             style="z-index: 9999">
       <!--Close after {{dismissCountDown}} seconds...<br />-->
      {{ message }}

    </b-alert>
  </div>
</template>

<script>
  import Bus from '../eventBus'
  export default {
    data () {
      return {
        dismissSecs: 2,
        dismissCountDown: 0,
        message: '',
        variant: ''
      }
    },
    created () {
      Bus.$on('alerts', (message) => {
        this.message = message
        this.variant = 'danger'
        this.showAlert()
      });
      Bus.$on('success', (message) => {
        this.message = message
        this.variant = 'success'
        this.showAlert()
      });
    },
    methods: {
      countDownChanged (dismissCountDown) {
        this.dismissCountDown = dismissCountDown
      },
      showAlert () {
        this.dismissCountDown = this.dismissSecs
      }
    }
  }
</script>
