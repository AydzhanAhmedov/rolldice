import { createStore } from "vuex"

const store = createStore({
  state: {
    chartData: {
      labels: [],
      datasets: [
        {
          label: "Dice rolls",
          backgroundColor: "#f87979",
          data: [],
        },
      ],
    },
  },
  mutations: {
    saveData(state, data) {
      state.chartData.labels = data.map((d) => d.roll);
      state.chartData.datasets[0].data = data.map((d) => d.count);
    }
  },
  actions: {
    results() {
      fetch("http://localhost:8080/api/dice/results").then((response) =>
        this.dispatch('fetchdata', response)
      );
    },
    diceRoll() {
      fetch("http://localhost:8080/api/dice/rolldice").then((response) => {
        this.dispatch('fetchdata', response)
      }
      );
    },
    async fetchdata(context, response) {
      let data = await response.json();
      this.commit('saveData', data)
    },
  },
  getters: {
    getChartData(state) {
      return state.chartData
    },
    dataLoaded(state) {
      return state.chartData.datasets[0].data.length
    }
  }
})

export default store