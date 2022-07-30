<template>
  <div class="vue-template">
    <div class="login">
      <div class="container jumbotron text-center">
        <form @submit.prevent="login" class="form-signin">
          <h4 class="h3 mb-5 font-weight-normal">Login</h4>
          <label for="email" class="sr-only">Password</label>
          <input
            type="email"
            id="email"
            class="form-control"
            placeholder="E-mail"
            v-model="email"
            @keyup.enter="login"
            required
          />
          <label for="password" class="sr-only">Password</label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="password"
            @keyup.enter="login"
            required
          />
          <div v-if="hasCredentialError" class="mt-3 alert alert-danger">
            {{ credentialError }}
          </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Login
          </button>
          <p class="mt-5 mb-3 text-muted">by Kenan Yasin SARIGÜL &copy; 2022</p>
          <a href="/register">Don't you have an account?</a>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { required, email } from "vuelidate/lib/validators";
export default {
  data() {
    return {
      submitted: false,
      password: null,
      email: null,
    };
  },
  validations: {
    email: { required, email },
    password: { required },
  },
  computed: {
    ...mapGetters("Auth", [
      "isLoggedIn",
      "account",
      "hasCredentialError",
      "credentialError",
    ]),
  },
  mounted() {
    this.$store.commit("Auth/setCredentialError", null);
    if (this.isLoggedIn) {
      this.$router.push("/profile");
    }
  },
  methods: {
    login() {
      this.submitted = true;
      this.$v.$touch();
      if (!this.$v.$invalid) {
        this.$store.dispatch("Auth/login", {
          email: this.email,
          password: this.password,
        });
      }
    },
  },
};
</script>

<style scoped>
html,
.jumbotron {
  max-width: 750px;
}
.login {
  height: 100%;
  width: 100%;
  margin-top: 10%;
  margin-bottom: 10%;
}
.login {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
}
.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}
.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
