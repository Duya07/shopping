<template>
  <div id="app">
    <home></home>
  </div>
</template>

<script>
  import home from './components/home.vue'

  export default {
    name: 'App',
    components: {
      home
    }
  }
</script>

<style>
#app {
  font-family: "Microsoft YaHei", San Francisco UI,sans-serif;
  height: 100%;
}
</style>
