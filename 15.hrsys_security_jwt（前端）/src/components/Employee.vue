<template>
    <div id="container">
        <el-row>
            <el-form :inline="true" :model="searchForm" class="demo-form-inline">
                <el-form-item>
                    <el-input v-model="searchForm.number" placeholder="编号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="searchForm.name" placeholder="姓名"></el-input>
                </el-form-item>
                <el-form-item style="width:120px">
                    <el-select v-model="searchForm.gender" placeholder="性别">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="searchForm.age" placeholder="年龄"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-select v-model="searchForm['dep.id']" placeholder="部门">
                        <el-option value="" label="选择部门"></el-option>
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
                    style="width: 100%" highlight-current-row @current-change="selectTr"
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
            <el-pagination
                    background
                    layout="prev, pager, next"
                    :page-size="pagination.size"
                    :total=pagination.total
                    @current-change="selectPage">
            </el-pagination>
        </el-row>
        <el-row style="margin-top:10px;text-align: left">
            <el-button type="primary" v-on:click="showAdd">新增</el-button>
            <el-button type="primary" v-on:click="showUpdate">修改</el-button>
            <el-button type="primary" v-on:click="deleteData">删除</el-button>
        </el-row>
        <el-dialog
                :title="type?'新增':'修改'"
                :visible.sync="dialogVisible"
                width="50%">
            <el-form ref="form" :model="form" label-width="80px" style="text-align: left">
                <el-form-item label="编号">
                    <el-input v-model="form.number"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="form.gender">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="form.age"></el-input>
                </el-form-item>

                <el-form-item label="部门">
                    <el-select v-model="form.dep.id" placeholder="请选择部门" style="width:100%">
                        <el-option v-for="dep in depList"
                                   :key="dep.id"
                                   :label="dep.name"
                                   :value="dep.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="save">保存</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>


<script>
    export default {
        name: "Employee",
        data() {
            return {
                type: true,//true为新增，false为修改
                dialogVisible: false,
                selectedId: -1,
                searchForm: {
                    number: null,
                    name: null,
                    gender: "",
                    age: null,
                    'dep.id': ""
                },
                form: {
                    id: null,
                    number: null,
                    name: null,
                    gender: null,
                    age: null,
                    dep: {
                        id: null
                    }
                },
                list: [],
                depList: [],
                pagination: {
                    size: 3,
                    total: null,
                    currentPage: 1
                }
            }
        },
        methods: {
            selectPage:function(currentPage) {
                this.pagination.currentPage = currentPage;
                this.search();
            },
            search: function () {
                this.searchForm.page = this.pagination.currentPage;
                this.searchForm.size = this.pagination.size;

                this.axios.get('api/emp', {params: this.searchForm})
                    .then(function (res) {
                        this.list = res.data.data;
                        this.pagination.total = res.data.total;

                    }.bind(this))
            },
            searchDep: function () {
                this.axios.get('api/dep')
                    .then(function (res) {
                        this.depList = res.data;
                    }.bind(this))
            },
            searchById: function () {
                this.axios.get('/emp/' + this.selectedId)
                    .then(function (res) {
                        this.form = res.data;
                        if (this.form.dep == null) {
                            this.form.dep = {id: this.depList[0].id};
                        }
                    }.bind(this))
            },
            selectTr: function (obj) {
                this.selectedId = obj.id;

            },
            showAdd: function () {
                this.searchDep();
                this.type = true;
                this.dialogVisible = true;

            },
            showUpdate: function () {
                if (this.selectedId > -1) {
                    this.searchDep();
                    this.searchById();
                    this.type = false;
                    this.dialogVisible = true;

                } else {
                    this.$alert('请选中数据', '警告', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.$message({
                                type: 'info',
                                message: `action: ${action}`
                            });
                        }
                    });
                }
            },
            save: function () {
                if (this.type) {
                    this.add();
                } else {
                    this.update();
                }
                this.clearData();
            },
            add: function () {
                this.axios.post('/emp', this.form)
                    .then(function (res) {
                        if (res.data == true) {
                            this.$message({
                                message: '保存成功',
                                type: 'success'
                            });
                            this.search();
                        } else {
                            this.$message.error('错了哦，这是一条错误消息');
                        }
                    }.bind(this))
            },
            update: function () {
                this.axios.post('/emp', this.form)
                    .then(function (res) {
                        if (res.data == true) {
                            this.$message({
                                message: '保存成功',
                                type: 'success'
                            });
                            this.search();
                        } else {
                            this.$message.error('错了哦，这是一条错误消息');

                        }
                    }.bind(this))
            },
            deleteData: function () {
                if (this.selectedId > -1) {
                    this.axios.delete('/emp/' + this.selectedId)
                        .then(function (res) {
                            if (res.data == true) {
                                this.search();
                            } else {
                                alert("系统错误");
                            }
                        }.bind(this))
                } else {
                    alert("请选中数据");
                }
            },
            clearData: function () {
                this.dialogVisible = false;
                this.selectedId = -1;
                this.form = {
                    id: null,
                    number: null,
                    name: null,
                    gender: null,
                    age: null,
                    dep: {
                        id: null
                    }
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