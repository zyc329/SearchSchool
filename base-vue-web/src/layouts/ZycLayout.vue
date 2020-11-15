<template>
    <div>
        <div style="width: 100%;height: 60px;background: rgba(255,255,255,0.8);position: absolute;">
            <div class="header-index-left">
                <span>榕城查只为您找到心仪的学府</span>
            </div>
            <div style="position: absolute;left: 50%;top:50%;transform: translate(-50%,-50%);">
                <span style="font-size: 30px;font-weight: bolder;font-family: 华文新魏;">读万卷书，行万里路</span>
            </div>
            <user-menu class="header-index-right"></user-menu>
        </div>
        <router-view/>
    </div>
</template>

<script>
  import UserMenu from "@/components/tools/UserMenu"

  export default {
    name: 'ZycLayout',
    components: {
      UserMenu
    }
  }
</script>

<style scoped>
    .header-index-left{
        left: 25px;
        font-size: 18px;
        font-family: 华文新魏;
        top: 50%;
        transform: translate(0,-50%);
        position: absolute;
    }
    .header-index-right{
        right: 25px;
        font-size: 22px;
        top: 50%;
        transform: translate(-50%,-50%);
        position: absolute;
    }
</style>
