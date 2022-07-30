import axios from "axios";

export default {
  save(data) {
    return axios.post(`/product`, data);
  },
  update(data) {
    return axios.put(`/product`, data);
  },
  getAll() {
    return axios.get(`/product`);
  },
  deleteById(data) {
    return axios.delete(`/product/${data}`);
  },
};
