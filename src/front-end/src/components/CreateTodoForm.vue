<template>
<div class="justify-content-center">
  <div>
    <h3 style="color:#6E973A; padding: 2%">할 일 추가</h3>
  </div>

  <form @submit.prevent="addTodo" style="padding: 3%">
    <div class="form-group">
      <v-text-field class="inputField input-name p-3 styled-input" placeholder="할 일" v-model="newTodo.title"/>
    </div>
    <div class="form-group">
      <v-textarea row="1"
                  auto-grow
                  row-height="5"
                  outlined
                  color="#333333"
                  placeholder="메모" v-model="newTodo.memo"/>
    </div>
    <v-btn style="text-align: end; background-color: #6E973A; color: white;" type="submit" class="btn btn-primary btn-block">추가</v-btn>
  </form>
</div>
</template>

<script>
export default {
  props: ["todos"],
  name: "CreateTodoForm",
  data: () => ({
    newTodo: {
      title: "",
      memo: "",
    }
  }),
  methods: {
    addTodo() {
      let todo = {
        'title': this.newTodo.title,
        'memo': this.newTodo.memo,
        'done': false,
        'mod': false
      }
      console.log(todo)
      this.$emit("create_todo", todo);
    },
    asdf() {
      this.$emit("create_todo", "hello")

    }
  }
}
</script>

<style scoped>

</style>
