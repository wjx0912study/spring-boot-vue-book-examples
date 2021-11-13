import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "Index",
        component: () =>
            import( "../views/Index.vue"),
        children:[
            {
                path: "/emp/show",
                name: "EmpShow",
                component: () =>
                    import( "../views/employee/Show.vue")
            },
            {
                path: "/emp/add",
                name: "EmpAdd",
                component: () =>
                    import( "../views/employee/Add.vue")
            },
            {
                path: "/emp/update",
                name: "EmpUpdate",
                component: () =>
                    import( "../views/employee/Update.vue")
            },
            //省略其他
        ]
    },

];

const router = new VueRouter({
    routes
});

export default router;
