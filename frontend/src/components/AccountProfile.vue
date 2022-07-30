<template>
  <div>
    <div><Navbar /></div>
    <div class="container jumbotron">
      <div class="container">
        <div class="row">
          <div class="col-md-3"></div>
          <div class="col-md-6">
            <h4 class="mb-5">Account Informations</h4>
            <form class="needs-validation" novalidate>
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="firstName">First name</label>
                  <input
                    type="text"
                    class="form-control"
                    id="firstName"
                    placeholder=""
                    v-model="entity.name"
                    required
                  />
                  <div class="invalid-feedback">
                    Valid first name is required.
                  </div>
                </div>
                <div class="col-md-6 mb-3">
                  <label for="lastName">Last name</label>
                  <input
                    type="text"
                    class="form-control"
                    id="lastName"
                    placeholder=""
                    v-model="entity.surname"
                    required
                  />
                  <div class="invalid-feedback">
                    Valid last name is required.
                  </div>
                </div>
              </div>

              <div class="mb-3">
                <label for="username">E-Mail</label>
                <div class="input-group">
                  <div class="input-group-prepend">
                    <span class="input-group-text">@</span>
                  </div>
                  <input
                    type="email"
                    class="form-control"
                    id="username"
                    placeholder="E-Mail"
                    v-model="entity.auth.email"
                    disabled
                    required
                  />
                  <div class="invalid-feedback" style="width: 100%">
                    Your e-mail is required.
                  </div>
                </div>
              </div>

              <div class="mb-3">
                <label for="birthday"
                  >Birthday <span class="text-muted">(Optional)</span></label
                >
                <input
                  type="text"
                  class="form-control"
                  id="v"
                  placeholder="dd/mm/yyy"
                  v-model="entity.birthday"
                />
                <div class="invalid-feedback">Please enter your birthday.</div>
              </div>

              <div class="mb-3">
                <label for="phonenumber"
                  >Phone Number
                  <span class="text-muted">(Optional)</span></label
                >
                <input
                  type="text"
                  class="form-control"
                  id="phonenumber"
                  placeholder="5XX-XXX-XX-XX"
                  v-model="entity.phone"
                  required
                />
                <div class="invalid-feedback">
                  Please enter your phone number
                </div>
              </div>

              <div class="mb-3">
                <label for="address"
                  >Address <span class="text-muted">(Optional)</span></label
                >
                <input
                  type="text"
                  class="form-control"
                  id="address"
                  placeholder="Istanbul Turkey"
                  v-model="entity.address"
                  required
                />
                <div class="invalid-feedback">
                  Please enter your shipping address.
                </div>
              </div>
              <button
                type="submit"
                @click="update"
                class="btn btn-success mt-5 btn-lg btn-block"
              >
                Update
              </button>
            </form>
          </div>
          <div class="col-md-3 mb-3"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from "../components/Navbar.vue";
import { mapGetters } from "vuex";
import axios from "axios";
import {
  required,
  minLength,
  maxLength,
  url,
  numeric,
} from "vuelidate/lib/validators";

export default {
  components: {
    Navbar,
  },
  data() {
    return {
      entity: { auth: {} },
      submitted: false,
    };
  },
  validations: {
    entity: {
      name: {
        required,
        maxLength: maxLength(64),
      },
      surname: {
        required,
        maxLength: maxLength(20),
      },
    },
  },
  computed: {
    ...mapGetters("Auth", ["account", "jwt"]),
  },
  async mounted() {
    this.getData();
  },
  methods: {
    async getData() {
      this.entity.id = this.account.id;
      const { data } = await axios.get(`/account`);
      this.entity = { ...data };
    },
    async update() {
      this.submitted = true;
      this.$v.$touch();
      if (!this.$v.$invalid) {
        await axios.put(`/account`, this.entity).then((r) => {
          this.$store.commit("Auth/setAccount", this.entity);
        });
        this.$toastr.success("Profil başarıyla güncellendi", "Başarılı!");
        setTimeout(() => {
          this.getData();
        }, 2000);
      } else {
        this.$toastr.error(
          "Lütfen geçerli profil bilgileri giriniz",
          "Başarısız!"
        );
      }
    },
  },
};
</script>

<style scoped>
.jumbotron {
  margin-top: 5%;
}
</style>
