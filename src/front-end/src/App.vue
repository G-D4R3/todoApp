<template>
  <v-app>
    <v-main>
      <v-row justify="center">
        <h1 style="color:#6E973A;" class="mt-10"> Todo App </h1>
      </v-row>
      <v-row justify="center">
        <v-flex xs4>
          <v-date-picker
              v-model="date"
              color="#80AB49"
              show-adjacent-months
              :allowed-dates="allowedDates"
              class="mt-4 mr-16"
              min="2021-01-01"
              max="2024-12-31"
          ></v-date-picker>
        </v-flex>
        <v-flex xs4 style="padding-top: 3%">
          <TodoList v-bind:date="childDate" :raw_date="date"></TodoList>
        </v-flex>
      </v-row>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
import TodoList from '@/components/TodoList'
export default {
  name: 'App',
  components: {
    TodoList
  },

  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    childDate : new Date().toISOString().substr(5, 5).replace('-', '월 ') + "일"
  }),

};
</script>
