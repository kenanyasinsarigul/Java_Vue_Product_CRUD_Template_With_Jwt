<template>
  <div>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <div class="container">
        <a class="navbar-brand" href="/"
          ><img style="width: 2em; height: 1.7em" src="../assets/home.png"
        /></a>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarCollapse"
          aria-controls="navbarCollapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item mr-2">
              <router-link
                v-if="isLoggedIn"
                class="menu btn btn-secondary btn-sm btn-block"
                to="/profile"
              >
                Profile</router-link
              >
            </li>
            <li class="nav-item mr-2">
              <router-link
                v-if="isLoggedIn"
                class="menu btn btn-secondary btn-sm btn-block"
                to="/addproduct"
              >
                Add Product</router-link
              >
            </li>
            <li class="nav-item mr-2">
              <router-link
                v-if="isLoggedIn"
                class="menu btn btn-secondary btn-sm btn-block"
                to="/products"
              >
                Products</router-link
              >
            </li>
            <li class="nav-item mr-2">
              <button
                v-if="isLoggedIn"
                type="submit"
                @click="logout"
                class="btn btn-danger btn-sm btn-block"
              >
                Logout
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters("Auth", ["isLoggedIn", "account"]),
  },
  methods: {
    logout() {
      this.$store.dispatch("Auth/logout");
    },
  },
};
</script>

<style scoped>
.menu:hover {
  transition: ease 0.5s;
  color: goldenrod;
}
</style>
