import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
//不建议直接操作state的属性
const state = {
  employee: false,
  department: false,
  role: false,
};
//get方法
const getters = {
  getEmployee() {
    return state.employee
  },
  getDepartment() {
    return state.department
  },
  getRole() {
    return state.role
  },
};
//同步方法
const mutations = {
  setEmployee(flag) {
    state.employee = flag;
  },
  setDepartment(flag) {
    state.department = flag;
  },
  setRole(flag) {
    state.role = flag;
  },

  clear() {
    state.employee = false;
    state.department = false;
    state.role = false;
  },
}
const store = new Vuex.Store({state, getters, mutations});
export default store;
