<template>
    <div id="container">
        <el-row>
            <el-form :inline="true" :model="form" class="demo-form-inline">
                <el-form-item>
                    <el-input v-model="form.number" placeholder="编号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="form.name" placeholder="姓名"></el-input>
                </el-form-item>
                <el-form-item style="width:120px">
                    <el-select v-model="form.gender" placeholder="性别">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="form.age" placeholder="年龄"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-select v-model="form['dep.id']" placeholder="部门">
                        <el-option v-for="dep in depList" :key="dep.id" :label="dep.name" :value="dep.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="search">查询</el-button>
                </el-form-item>
            </el-form>
        </el-row>
        <el-row>
            <el-table
                    :data="list"
                    border
                    style="width: 100%" highlight-current-row  @current-change="selectTr"
            >
                <el-table-column
                        prop="number"
                        label="编号"
                        width="200"
                >
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="gender"
                        label="性别"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="age"
                        label="年龄"
                        width="200"></el-table-column>

                <el-table-column
                        prop="dep.name"
                        label="部门"
                >
                </el-table-column>
            </el-table>
        </el-row>
        <el-row style="margin-top:10px;text-align: left">
            <el-button type="primary" v-on:click="showAdd">新增</el-button>
            <el-button type="primary" v-on:click="showUpdate">修改</el-button>
            <el-button type="primary" v-on:click="deleteData">删除</el-button>
        </el-row>
    </div>
</template>


<script>
    export default {
        name: "Department",
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
            selectTr: function (obj) {
                this.selectedId = obj.id;

            },
            showAdd: function () {
                this.$router.push({name: "EmpAdd"})
            },
            showUpdate: function () {
                if (this.selectedId > -1) {
                    this.$router.push({name: "EmpUpdate", params: {id: this.selectedId}})
                } else {
                    this.$alert('请选中数据', '警告', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.$message({
                                type: 'info',
                                message: `action: ${ action }`
                            });
                        }
                    });                }
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

</style>