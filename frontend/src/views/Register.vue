<template>
  <div class="vue-tempalte">
    <div class="register">
      <div class="container jumbotron text-center">
        <form @submit.prevent="register" class="form-signin">
          <h1 class="h3 mb-5 font-weight-normal">Register</h1>
          <label for="name" class="sr-only">Name</label>
          <input
            type="text"
            id="name"
            class="form-control"
            placeholder="Name"
            v-model="registerRequest.name"
            required
          />
          <label for="surname" class="sr-only">Password</label>
          <input
            type="text"
            id="surname"
            class="form-control"
            placeholder="Surname"
            v-model="registerRequest.surname"
            required
          />
          <label for="email" class="sr-only">Password</label>
          <input
            type="email"
            id="email"
            class="form-control"
            placeholder="E-mail"
            v-model="registerRequest.email"
            required
          />
          <label for="password" class="sr-only">Password</label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="registerRequest.password"
            :class="{
              'is-invalid': submitted && $v.registerRequest.password.$error,
            }"
            required
          />
          <div
            v-if="submitted && !$v.registerRequest.password.validate"
            class="invalid-feedback mb-3"
          >
            Parola min:6 - max:32 karakter olmalıdır!
          </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Register
          </button>
          <p class="mt-5 mb-3 text-muted">by Kenan Yasin SARIGÜL &copy; 2022</p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import AuthClient from "../client/Auth";
import {
  required,
  email,
  minLength,
  maxLength,
} from "vuelidate/lib/validators";
export default {
  data() {
    return {
      registerRequest: {
        name: null,
        surname: null,
        email: null,
        password: null,
      },
      submitted: false,
    };
  },
  validations: {
    registerRequest: {
      name: { required },
      surname: { required },
      email: { required, email },
      password: { required, minLength: minLength(6), maxLength: maxLength(32) },
    },
  },
  mounted() {},
  methods: {
    async register(e) {
      this.submitted = true;
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }
      try {
        const data = await AuthClient.register(this.registerRequest);
        this.$toastr.success("kullanıcınız başarıyla oluşturuldu", "Başarılı!");
        setTimeout(() => {
          this.$router.push("/");
        }, 2000);
      } catch (error) {
        this.$toastr.error("Bu kullanıcı adı kullanılmaktadır.", "Başarısız!");
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
.register {
  height: 100%;
  width: 100%;
  margin-top: 7%;
  margin-bottom: 7%;
}
.register {
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
