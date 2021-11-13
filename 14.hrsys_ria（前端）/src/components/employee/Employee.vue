<template>
    <div id="container">
        <el-row>
            <Search @search="search"/>
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
        </el-row>
        <el-row style="margin-top:10px;text-align: left">
            <el-button type="primary" v-on:click="showAdd">新增</el-button>
            <el-button type="primary" v-on:click="showUpdate">修改</el-button>
            <el-button type="primary" v-on:click="deleteData">删除</el-button>
        </el-row>
        <AddOrUpdate  ref="AddOrUpdate" @search="search"></AddOrUpdate>
    </div>
</template>


<script>
    import Search from '@/components/employee/Search.vue';
    import AddOrUpdate from '@/components/employee/AddOrUpdate.vue';

    export default {
        name: "Employee",
        components: {
            AddOrUpdate,
            Search
        },
        data() {
            return {
                selectedId: -1,
                list: [],
            }
        },
        methods: {
            search: function (searchForm) {
                this.axios.get('/emp', {params: searchForm})
                    .then(function (res) {
                        this.list = res.data;
                    }.bind(this))
            },
            selectTr: function (obj) {
                this.selectedId = obj.id;

            },
            showAdd: function () {
                this.$refs.AddOrUpdate.dialogVisible = true;
                this.$refs.AddOrUpdate.type = true;

            },
            showUpdate: function () {
                if (this.selectedId > -1) {
                    this.$refs.AddOrUpdate.dialogVisible = true;
                    this.$refs.AddOrUpdate.type = false;
                    this.$refs.AddOrUpdate.selectedId= this.selectedId;
                    this.$refs.AddOrUpdate.init();
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
        },
        created() {
            this.search();
        }
    }
</script>

<style scoped>

</style>