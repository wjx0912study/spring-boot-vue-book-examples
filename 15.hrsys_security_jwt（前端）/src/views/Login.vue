<template>
  <div style="width:600px;margin:150px auto">

    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="账号">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doLogin">登录</el-button>
        <el-button >重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                form: {
                    username: "",
                    password: ""

                }


            }
        },
        methods: {
            doLogin() {
                this.axios.post('api/doLogin', this.qs.stringify(this.form))
                    .then(function (res) {
                        let mes = "";
                        if (res.data.code == 1) {
                            mes = "登录成功";
                          /** 将Token保存到localStorage*/
                          const authorization = res.data.authorization;
                          localStorage.token = authorization;
                            this.$router.replace({name: 'Index'});
                        } else if (res.data.code == 2) {
                            mes = "登录失败";
                        }

                        this.$message({
                            message: mes,
                            type: 'success',
                            duration: 1000
                        });
                    }.bind(this))
                    .catch(function (err) {
                        if (err.response) {
                            console.log(err.response)
                        }
                    }.bind(this))
            }
        }
    }
</script>

<style scoped>

</style>
