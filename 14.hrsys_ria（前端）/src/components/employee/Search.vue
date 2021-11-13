<template>

            <el-form :inline="true" :model="searchForm" class="demo-form-inline">
                <el-form-item>
                    <el-input v-model="searchForm.number" placeholder="编号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="searchForm.name" placeholder="姓名"></el-input>
                </el-form-item>
                <el-form-item style="width:120px">
                    <el-select v-model="searchForm.gender" placeholder="性别">
                        <el-option label="选择性别" value=""></el-option>
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
</template>


<script>
    export default {
        name: "Search",
        data() {
            return {
                type: true,//true为新增，false为修改
                dialogVisible: false,
                searchForm: {
                    number: null,
                    name: null,
                    gender: "",
                    age: null,
                    'dep.id': ""
                },
                depList: []
            }
        },
        methods: {
            searchDep: function () {
                this.axios.get('/dep')
                    .then(function (res) {
                        this.depList = res.data;
                    }.bind(this))
            },
            search:function(){
                this.$emit("search",this.searchForm)
            }
        },
        created() {
            this.searchDep();
        }
    }
</script>

<style scoped>

</style>