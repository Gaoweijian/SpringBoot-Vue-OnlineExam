<template>
  <div class="body">
    <quill-editor ref="text" v-model="content" class="editor" :options="editorOption"/>
    <el-button class="button" @click="onEditor">提交</el-button>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        content: "",
        editorOption: {},
      };
    },
    methods: {
      onEditor() {
        console.log("富文本内容=",this.$refs.text.value);
      },
    },
  };
</script>

<style lang="less" scoped>
  .body {
    height: 700px;
    .editor {
      height: 500px;
    }
    .button {
      margin-left: 50%;
      margin-top: 50px;
    }
  }
</style>
