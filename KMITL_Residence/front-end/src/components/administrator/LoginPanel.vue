<template>
    <div>
        <div class="login-container extra-top-menubar-padding">
            <div class="card-padding">
                <div class="card padding-content normal-bottom-padding">
                    <div class="ui grid">
                        <div class="sixteen wide column">
                            <h1 class="ui center aligned header section-header">
                                Login!
                            </h1>
                        </div>
                        <div class="sixteen wide column">
                            <LoginForm/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import LoginForm from './LoginForm'
export default {
    components: {
        LoginForm
    }
}
</script>