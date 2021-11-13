<template>
    <div id="container">
        <div>这是ViewP视图组件</div>
        <router-link :to="{path:'/viewa'}">ViewA</router-link>
        |
        <router-link to="/viewb">ViewB</router-link>
        <Button @click="routerLink('ViewA')">ViewA</Button>
        <Button @click="routerLink('ViewB')">ViewB</Button>
        <router-view/>
    </div>
</template>

<script>
    export default {
        name: "ViewP",
        methods: {
            routerLink: function (viewName) {
                this.$router.push({path:viewName,query:{id:1}});

            }
        }
    }
</script>

<style scoped>
    #container {
        width: 800px;
        margin: 10px auto;
    }
</style>
