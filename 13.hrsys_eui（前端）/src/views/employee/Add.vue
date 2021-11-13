<template>
    <div id="container">
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
                <el-button type="primary" @click="add">保存</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Add",
        data() {
            return {
                form: {
                    number: null,
                    name: null,
                    gender: null,
                    age: null,
                    dep: {
                        id: null
                    }
                },
                depList: []
            }
        },
        methods: {
            add: function () {
                this.axios.post('/emp', this.form)
                    .then(function (res) {
                        if (res.data == true) {
                            this.$message({
                                message: '保存成功',
                                type: 'success'
                            });
                            this.$router.push({name: "EmpShow"})
                        } else {
                            this.$message.error('错了哦，这是一条错误消息');
                        }
                    }.bind(this))
            },
            searchDep: function () {
                this.axios.get('/dep')
                    .then(function (res) {
                        this.depList = res.data;
                        this.form.dep.id = this.depList[0].id
                    }.bind(this))
            },

        },
        created() {
            this.searchDep();
        }
    }
</script>

<style scoped>
    #container {
        width: 600px;
        margin: 10px auto;
    }
</style>