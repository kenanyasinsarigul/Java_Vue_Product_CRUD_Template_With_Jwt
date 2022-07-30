<template>
  <div>
    <div v-if="isLoggedIn"><Navbar /></div>
    <div class="container jumbotron text-center">
      <div class="row">
        <div class="col-md-6">
          <img
            src="../assets/me.png"
            class="rounded-circle"
            alt="..."
            style="width: 75%; height: 100%"
          />
        </div>
        <div class="col-md-6">
          <div class="text-right mt-2" style="margin-right: 20%">
            <h1>Kenan Yasin SARIGÜL</h1>
            <h5 style="font-style: italic">Fullstack Software Developer</h5>
            <br />
            <p
              style="
                font-family: Courier;
                font-style: italic;
                font-weight: bold;
              "
            >
              " This project has been developed as a package that can be used to
              lay the foundations of a new project for authentication and user
              profile operations."
            </p>
            <br />
            <p class="text-muted" style="font-style: normal; font-weight: bold">
              kenansarigul97@gmail.com
            </p>
            <br />
            <div class="float-right" style="width: 25%">
              <a
                v-if="!isLoggedIn"
                class="btn btn-success btn-sm btn-block"
                style="font-weight: 500"
                href="/login"
                >Login - ></a
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Navbar from "../components/Navbar.vue";
export default {
  components: {
    Navbar,
  },
  computed: {
    ...mapGetters("Auth", ["isLoggedIn", "account"]),
  },
};
</script>

<style scoped>
.jumbotron {
  margin-top: 10%;
}
</style>
