<template>
  <div>
    <div><Navbar /></div>
    <div class="container jumbotron">
      <table class="table table-bordered table-hover text-center">
        <thead class="thead-dark">
          <tr>
            <th class="col-1">ID</th>
            <th class="col-3">Name</th>
            <th class="col-2">Type</th>
            <th class="col-2">Count</th>
            <th class="col-2">Cost</th>
            <th class="col-2">Operations</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in list" v-bind:key="product.id">
            <th>{{ product.id }}</th>
            <td>{{ product.productName }}</td>
            <td>{{ product.productType }}</td>
            <td>{{ product.productCount }}</td>
            <td>{{ product.productCost }}</td>
            <td>
              <button
                class="btn btn-primary mr-2 btn-sm"
                data-toggle="modal"
                data-target="#exampleModal"
                @click="selectProduct(product.id)"
              >
                Update
              </button>
              <button
                class="btn btn-danger mr-2 btn-sm"
                @click="deleteById(product.id)"
              >
                delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- Modal -->
    <div
      class="modal fade"
      id="exampleModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Update Product</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="mb-3">
              <label for="productName">Product Name </label>
              <input
                type="text"
                class="form-control"
                id="productName"
                placeholder="Product Name"
                v-model="selectedProduct.productName"
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
                v-model="selectedProduct.productType"
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
                v-model="selectedProduct.productCount"
                required
              />
              <div class="invalid-feedback">
                Please enter your product count
              </div>
            </div>

            <div class="mb-3">
              <label for="productCost">Product Cost </label>
              <input
                type="number"
                class="form-control"
                id="productCost"
                placeholder="Product Cost"
                v-model="selectedProduct.productCost"
                required
              />
              <div class="invalid-feedback">Please enter your product cost</div>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
            >
              Close
            </button>
            <button
              type="button"
              class="btn btn-primary"
              @click="update(selectedProduct)"
            >
              Save changes
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Navbar from "./Navbar.vue";
import ProductClient from "../client/Product";
export default {
  components: {
    Navbar,
  },
  data() {
    return {
      list: null,
      submitted: false,
      selectedProduct: {},
    };
  },
  computed: {
    ...mapGetters("Auth", ["account", "jwt"]),
  },
  mounted() {
    this.getAll();
  },
  methods: {
    async getAll() {
      const data = await ProductClient.getAll();
      this.list = data.data;
    },
    async update(request) {
      try {
        await ProductClient.update(request);
        this.$toastr.success("Ürün başarıyla güncellendi", "Başarılı!");
      } catch (error) {
        this.$toastr.error("Hata.", "Başarısız!");
      }
      this.getAll();
    },
    async deleteById(id) {
      if (!confirm("Delete it?")) {
        return;
      }
      try {
        await ProductClient.deleteById(id);
        this.$toastr.success("Ürün başarıyla silindi", "Başarılı!");
      } catch (error) {
        this.$toastr.error("Hata.", "Başarısız!");
      }
      this.getAll();
    },
    selectProduct(id) {
      const data = this.list.find((i) => (i.id = id));
      this.selectedProduct = { ...data };
    },
  },
};
</script>

<style scoped>
.jumbotron {
  margin-top: 5%;
}
</style>
