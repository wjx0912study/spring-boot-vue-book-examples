<template>
    <el-container>
        <el-header style="height:120px">
            <div id="top">
                <div id="logo">Alan人事管理系统</div>
            </div>
        </el-header>
        <el-container>
            <el-aside width="200px">
                <el-menu>
                    <el-menu-item><i class="el-icon-s-home"></i>欢迎页面</el-menu-item>
                    <el-menu-item index="employee"><i class="el-icon-s-custom"></i>
                        <router-link to="/emp/show">员工管理</router-link>
                    </el-menu-item>
                    <el-menu-item index="department"><i class="el-icon-s-management"></i>部门管理</el-menu-item>
                    <el-submenu index="permission_management">
                        <template slot="title"><i class="el-icon-setting"></i>权限管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="user">用户管理</el-menu-item>
                            <el-menu-item index="role">角色管理</el-menu-item>
                            <el-menu-item index="permission">权限管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
        <el-footer style="height:120px">Footer</el-footer>
    </el-container>
</template>

<script>
    export default {
        name: "Index",
    }
</script>

<style scoped>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
    }

    .el-aside {
        color: #333;
        text-align: center;
        height: 400px;
    }

    .el-main {
        background-color: #fff;
        color: #333;
        text-align: center;
    }

    #top {
        clear: both;
        height: 80px;
        text-align: left;
    }

    #top #logo {
        color: #fff;
        font-size: 30px;
        font-weight: bold;
        padding: 15px 0 0 40px;
    }
</style>
