<template>
    <div id="container">
        <div>
            {{name}}
            {{message}}
        </div>
        <button @click="change">点击</button>
        <button @click="sendMes">发送消息到父组件</button>
    </div>
</template>

<script>
    export default {
        name: "A",
        props: {
            name: String
        },
        data() {
            return {
                message: "你好，我是A组件",
            }
        },
        methods: {
            change: function () {
                this.message = "Hello,this is A component"
            },
            sendMes: function () {
                this.$emit("receiveMes","这是A组件发送到父组件的信息")
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
