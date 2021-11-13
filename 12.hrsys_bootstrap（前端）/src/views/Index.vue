<template>
    <div id="container">
        <div id="top">
            <div id="logo">Alan人事管理系统</div>
        </div>
        <div id="main">
            <div id="left">
                <div class="yi">员工管理</div>
                <ul class="er">
                    <li>
                        <router-link to="/emp/show">员工管理</router-link>
                    </li>
                    <li>
                        <router-link to="/emp/add">员工添加</router-link>
                    </li>
                </ul>
                <div class="yi">部门管理</div>
                <ul class="er">
                    <li><router-link to="/dep/show">部门管理</router-link></li>
                    <li><router-link to="/dep/add">部门添加</router-link></li>
                </ul>
                <div class="yi">权限管理</div>
                <ul class="er">
                    <li><router-link to="/sysUser/show">用户管理</router-link></li>
                    <li><router-link to="/sysRole/show">角色管理</router-link></li>
                    <li><router-link to="/sysPermission/show">权限管理</router-link></li>
                </ul>
            </div>
            <div id="right">
                <router-view/>
            </div>
        </div>
        <div id="bottom">

        </div>
    </div>
</template>

<script>
    export default {
        name: "Index",
        created() {
            this.$router.push("/emp/show");
        }
    }
</script>

<style scoped>
    * {
        margin: 0;
        padding: 0;
    }

    #top, #bottom {
        clear: both;
        height: 80px;
        background: #337ab7;
        height: 80px;
    }

    #top #logo {
        color: #fff;
        font-size: 30px;
        font-weight: bold;
        padding: 15px 0 0 40px;
    }

    #main {
        margin-top: 10px;
        overflow: hidden;
    }

    a {
        color: #fff;
        text-decoration: none;
    }

    #left {
        width: 240px;
        float: left;
        height: 460px;
        padding-left: 20px;
    }

    #right {
        width: 800px;
        float: left;
        height: 460px;
    }


    .yi {
        width: 160px;
        height: 40px;
        background: #337ab7;
        color: #fff;
        margin-top: 5px;
        border-radius: 3px;
        text-align: center;
        line-height: 40px;
    }

    .er {
        list-style: none;
        width: 160px;
    }

    .er li {
        margin-top: 2px;
        text-align: center;
        background: #337aae;
        color: #fff;
        height: 30px;
        line-height: 30px;
        font-size: 14px;
    }
</style>