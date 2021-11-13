<template>
    <el-container>
        <el-header style="height:120px">
            <div id="top">
                <div id="logo">Alan人事管理系统</div>
            </div>
        </el-header>
        <el-container>
            <el-aside width="200px">
                <el-menu @select="selectMenu">
                    <el-menu-item><i class="el-icon-s-home"></i>欢迎页面</el-menu-item>
                    <el-menu-item index="employee"><i class="el-icon-s-custom"></i>
                        员工管理
                    </el-menu-item>
                    <el-menu-item index="department"><i class="el-icon-s-management"></i>部门管理</el-menu-item>
                    <el-submenu index="permission_management">
                        <template slot="title"><i class="el-icon-setting"></i>权限管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="sysUser">用户管理</el-menu-item>
                            <el-menu-item index="sysRole">角色管理</el-menu-item>
                            <el-menu-item index="sysPermission">权限管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-main>
                <el-tabs v-model="selectedTab" type="card" closable @tab-remove="removeTab">
                    <el-tab-pane
                            :key="modules[key].name"
                            v-for="key in tabs"
                            :label="modules[key].title"
                            :name="modules[key].name"
                    >
                        <component :is="modules[key].component"></component>
                    </el-tab-pane>

                </el-tabs>

            </el-main>
        </el-container>
        <el-footer style="height:120px">Footer</el-footer>
    </el-container>
</template>

<script>
    import Employee from '@/components/Employee.vue'
    import Department from '@/components/Department.vue'
    import SysUser from '@/components/SysUser.vue'
    import SysRole from '@/components/SysRole.vue'
    import SysPermission from '@/components/SysPermission.vue'

    export default {
        name: "Index",
        data() {
            return {
                selectedTab: null,
                tabs: [],
                modules: {
                    employee: {
                        title: '员工管理',
                        name: 'employee',
                        component: Employee
                    },
                    department: {
                        title: '部门管理',
                        name: 'department',
                        component: Department
                    }
                    ,
                    sysUser: {
                        title: '用户管理',
                        name: 'sysUser',
                        component: SysUser
                    }
                    ,
                    sysRole: {
                        title: '角色管理',
                        name: 'sysRole',
                        component: SysRole
                    }
                    ,
                    sysPermission: {
                        title: '权限管理',
                        name: 'sysPermission',
                        component: SysPermission
                    }
                }
            }
        },

        methods: {
            selectMenu(index) {
                if (this.tabs.indexOf(index) < 0) {
                    this.tabs.push(index);
                }
                this.selectedTab = index;
            },
            removeTab(name) {
                let index=this.tabs.indexOf(name);
                this.tabs.splice(index,1)

                this.selectedTab = this.tabs[0];
            }
        }
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
