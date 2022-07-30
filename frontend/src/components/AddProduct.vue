<template>
  <div>
    <div><Navbar /></div>
    <div class="container jumbotron">
      <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
          <h4 class="mb-5">Add product</h4>
          <div class="mb-3">
            <label for="productName">Product Name </label>
            <input
              type="text"
              class="form-control"
              id="productName"
              placeholder="Product Name"
              v-model="request.productName"
              required
            />
            <div class="invalid-feedback">Please enter your product name</div>
          </div>

          <div class="mb-3">
            <label for="productType">Product Type </label>
            <input
              type="text"
              class="form-control"
              id="productType"
              placeholder="Product Type"
              v-model="request.productType"
              required
            />
            <div class="invalid-feedback">Please enter your product type</div>
          </div>

          <div class="mb-3">
            <label for="productCount">Product Count </label>
            <input
              type="number"
              class="form-control"
              id="productCount"
              placeholder="Product Count"
              v-model="request.productCount"
              required
            />
            <div class="invalid-feedback">Please enter your product count</div>
          </div>

          <div class="mb-3">
            <label for="productCost">Product Cost </label>
            <input
              type="number"
              class="form-control"
              id="productCost"
              placeholder="Product Cost"
              v-model="request.productCost"
              required
            />
            <div class="invalid-feedback">Please enter your product cost</div>
          </div>

          <button
            type="submit"
            @click="save"
            class="btn btn-success mt-5 btn-lg btn-block"
          >
            Add
          </button>
        </div>
        <div class="col-md-3 mb-3"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Navbar from "./Navbar.vue";
import ProductClient from "../client/Product";
import { required } from "vuelidate/lib/validators";
export default {
  components: {
    Navbar,
  },
  validations: {
    request: {
      productName: { required },
      productType: { required },
      productCount: { required },
      productCost: { required },
    },
  },
  data() {
    return {
      request: {
        id: null,
        productName: null,
        productType: null,
        productCount: null,
        productCost: null,
      },
      submitted: false,
    };
  },
  computed: {
    ...mapGetters("Auth", ["account", "jwt"]),
  },
  methods: {
    async save(e) {
      this.submitted = true;
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }
      try {
        const data = await ProductClient.save(this.request);
        this.$toastr.success("Ürün başarıyla oluşturuldu", "Başarılı!");
        setTimeout(() => {
          this.$router.push("/products");
        }, 1000);
      } catch (error) {
        this.$toastr.error("Hata.", "Başarısız!");
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
