<template>
    <div id="container">
        这是ViewA视图组件
        <div>获得的参数id：{{id}}</div>
    </div>
</template>

<script>
    export default {
        name: "ViewA",
        data() {
            return {
                id:null
            }
        },
        created(){
            this.id=this.$route.query.id;
        }
    }
</script>

<style scoped>
    #container {
        width: 800px;
        margin: 10px auto;
    }
</style>
