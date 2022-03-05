<template>
  <div>
    <h3 style="color:#6E973A; margin-left: 10px">{{this.date}}</h3>
    <v-list
        flat
        subheader
    >
      <v-subheader>To Do</v-subheader>
      <v-list-item v-for="todo in todos" :key="todo.title">
        <v-flex style="padding: 3%">
          <v-row>
            <v-list-item-action>
              <v-checkbox v-model="todo.done" @change="check(todo)"></v-checkbox>
            </v-list-item-action>
            <v-list-item-content @click="modifyClick(todo)" v-if="todo.mod==false">
              <v-list-item-title v-text="todo.title"></v-list-item-title>
              <v-list-item-subtitle class="text-wrap" v-text="todo.memo"></v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-content v-if="todo.mod==true" class="text-wrap">
              <form style="min-width: 200px" @keyup.enter="modify(todo)">
                <div class="form-group">
                  <v-text-field class="inputField input-name p-3 styled-input" type="text" v-model="modTodo.title"/>
                </div>
                <div class="form-group">
                  <v-textarea
                      row="1"
                      auto-grow
                      row-height="5"
                      outlined
                      v-model="modTodo.memo"
                  ></v-textarea>
                </div>
              </form>
            </v-list-item-content>
            <v-list-item-action v-if="todo.mod==false">
              <v-icon
                  color="red"
                  @click="remove(todo)">
                mdi-minus-circle
              </v-icon>
            </v-list-item-action>
          </v-row>
        </v-flex>
      </v-list-item>
    </v-list>
    <v-row style="padding: 5%">
      <v-icon v-if="create==false" @click="create=true" color="green">
        mdi-plus-circle
      </v-icon>
      <v-icon v-if="create==true" @click="create=false" color="red">
        mdi-minus-circle
      </v-icon>
    </v-row>
    <v-flex v-if="create==true">
      <v-card style="padding: 5%">
        <CreateTodoForm v-bind:todos="todos" @create_todo="addTodo"></CreateTodoForm>
      </v-card>
    </v-flex>
    <v-btn @click="get">get</v-btn>
  </div>
</template>

<script>
import CreateTodoForm from "@/components/CreateTodoForm";
import axios from 'axios';

export default {
  name: "Todo",
  components: {CreateTodoForm},
  props: ["raw_date", "date"],
  data: () => ({
    create: false,
    todos: [{
      id: 1,
      title: "Notifications",
      memo: "Notify me about updates to apps or games that I downloaded",
      done: true,
      mod: false,
    }, {
      id: 2,
      title: "Hellop",
      memo: "Test on static data",
      done: false,
      mod: false,
    }],
    newTodo: {
      title: "",
      memo: "",
    },
    modTodo: {
      id: 0,
      title: "",
      memo: "",
    }
  }),
  created() {
    // get todo list by date
    axios.get('http://localhost:8080/todo', {
      params: {
        date: this.raw_date
      }
    }).then(res => {
      for(let i=0; i<res.data.length; i++){
        let todo = {
          id: res.data[i].id,
          title: res.data[i].title,
          memo: res.data[i].memo,
          done: res.data[i].done==0? false:true,
          mod: false
        }
        console.log(todo);
        this.todos.push(todo);
      }
    }).catch(e => {
      console.log(e);
    })
  },
  methods: {
    check(todo) {
      console.log(todo)
      // 체크 박스 state가 change 되었을 때 바로 데이터베이스에 업데이트
    },
    modifyClick (todo) {
      for(let i=0; i<this.todos.length; i++){
        if(this.todos[i]!=todo && this.todos[i].mod==true){
          this.todos[i].mod = false
        }
      }
      //alert(todo.title)
      todo.mod= true
      this.modTodo.title = todo.title
      this.modTodo.memo = todo.memo
    },
    modify(todo) {
      todo.title = this.modTodo.title
      todo.memo = this.modTodo.memo
      todo.mod = false
      // axios modify
    },
    addTodo (create_todo) {
      //this.create = !this.create;
      create_todo.date = this.raw_date;
      this.todos.push(create_todo)
      // axios add
    },
    remove (todo) {
      if(confirm(todo.title+"를 삭제하시겠습니까?")){
        this.todos.pop(todo)
        // axios remove
      }
    },
    get() {
      axios.get('http://localhost:8080/todo', {
        params: {
          date: this.raw_date
        }
      }).then(res => {
        for(let i=0; i<res.data.length; i++){
          let todo = {
            id: res.data[i].id,
            title: res.data[i].title,
            memo: res.data[i].memo,
            done: res.data[i].done==0? false:true,
            mod: false
          }
          console.log(todo);
          this.todos.push(todo);
        }
      }).catch(e => {
        console.log(e);
      })
    }
  }
}
</script>

