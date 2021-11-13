<template>
  <div class="home">
    <div>{{message}}</div>
    <component v-bind:is="comp" name="Jim"  @receiveMes="changeMes"></component>

  </div>
</template>

<script>
  import A from "@/components/A.vue"

  export default {
    name: "Home",
    components: {
      A
    },
    data() {
      return {
        message: null,
        comp: "A"
      }
    },
    methods: {
      changeMes: function (mes) {
        this.message = mes;
      }
    },
  };
</script>