<template>
    <div id="container">
        <div class="form-horizontal">
            <form class="form-group" @submit.prevent="search()">
                <div class="col-sm-2">
                    <input type="text" class="form-control"
                           placeholder="编号" v-model="form.number">
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control"
                           placeholder="姓名" v-model="form.name">
                </div>
                <div class="col-sm-2 ">
                    <select name="gender" class="form-control" v-model="form.gender">
                        <option value="">性别</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="年龄" v-model="form.age">
                </div>
                <div class="col-sm-2 ">
                    <select class="form-control" v-model="form['dep.id']">
                        <option value="">部门</option>
                        <option v-for="dep in depList"
                                v-text="dep.name" v-bind:key="dep.id" v-bind:value="dep.id">
                        </option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-primary">搜索</button>
                </div>
            </form>
        </div>
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>名字</th>
                <th>性别</th>
                <th>年龄</th>
                <th>部门</th>
            </tr>
            </thead>
            <tbody>
            <tr class="data" v-for="emp in list" v-bind:key="emp.id" v-bind:class="{selected:emp.id==selectedId}"
                @click="selectTr(emp.id)">
                <td v-text="emp.number"></td>
                <td v-text="emp.name"></td>
                <td v-text="emp.gender"></td>
                <td v-text="emp.age"></td>
                <td v-text="emp.dep!=null?emp.dep.name:''"></td>
            </tr>
            </tbody>
        </table>
        <div id="buttons">
            <button type="button" class="btn btn-primary" @click="showAdd">新增</button>
            <button type="button" class="btn btn-primary" @click="showUpdate">修改</button>
            <button type="button" class="btn btn-danger" @click="deleteData">删除</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Employee",
        data() {
            return {
                selectedId: -1,
                form: {
                    number: null,
                    name: null,
                    gender: "",
                    age: null,
                    'dep.id': ""
                },
                list: [],
                depList: []
            }
        },
        methods: {
            search: function () {
                this.axios.get('/emp', {params: this.form})
                    .then(function (res) {
                        this.list = res.data;
                    }.bind(this))
            },
            searchDep: function () {
                this.axios.get('/dep')
                    .then(function (res) {
                        this.depList = res.data;
                    }.bind(this))
            },
            selectTr: function (id) {
                this.selectedId = id;

            },
            showAdd: function () {
                this.$router.push({name: "EmpAdd"})
            },
            showUpdate: function () {
                if (this.selectedId > -1) {
                    this.$router.push({name: "EmpUpdate", params: {id: this.selectedId}})
                } else {
                    alert("请选中数据");
                }
            },
            deleteData: function () {
                if (this.selectedId > -1) {
                    this.axios.delete('/emp/' + this.selectedId)
                        .then(function (res) {
                            if (res.data == true) {
                                this.search();
                            }else {
                                alert("系统错误");
                            }
                        }.bind(this))
                } else {
                    alert("请选中数据");
                }
            }
        },
        created() {
            this.search();
            this.searchDep();
        }
    }
</script>

<style scoped>
    #container {
        width: 800px;
        margin: 10px auto;
    }

    #container .selected {
        background: #337ab7
    }

    #buttons button {
        margin-right: 5px;
    }
</style>