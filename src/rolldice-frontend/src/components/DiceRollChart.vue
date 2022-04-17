<script>
import { Bar } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

export default {
  components: { Bar },
  data() {
    return {
      data: [],
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
    };
  },
  mounted() {
    fetch("http://localhost:8080/api/dice/results").then((response) =>
      this.fetchdata(response)
    );
  },
  methods: {
    diceRoll() {
      fetch("http://localhost:8080/api/dice/rolldice").then((response) =>
        this.fetchdata(response)
      );
    },
    async fetchdata(response) {
      this.data = await response.json();
      this.chartData.labels = this.data.map((d) => d.roll);
      this.chartData.datasets[0].data = this.data.map((d) => d.count);
    },
  },
};
</script>

<template>
  <div class="hero">
    <div>
      <button @click="diceRoll()">Roll dice</button>
    </div>
    <div class="bar" v-if="this.data.length">
      <Bar :chart-data="chartData" />
    </div>
    <div v-else>
      <p>No data</p>
    </div>
  </div>
</template>

<style scoped>
button {
  font-size: 27px;
  text-transform: uppercase;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 5px;
  cursor: pointer;
  padding: 15px;
  border-radius: 50px;
  border: 0;
  box-shadow: -6px 8px 10px rgba(81, 41, 10, 0.1),
    0px 2px 2px rgba(81, 41, 10, 0.2);
  background: rgb(2, 0, 36);
  background: linear-gradient(
    180deg,
    rgba(2, 0, 36, 1) 0%,
    rgba(238, 194, 105, 1) 0%,
    rgba(242, 245, 163, 1) 100%
  );
}

button:hover {
  background: linear-gradient(
    180deg,
    rgba(2, 0, 36, 1) 0%,
    rgb(230, 47, 108) 0%,
    rgb(230, 29, 96) 100%
  );

  color: #fff;
}

.hero {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align-last: center;
  flex-direction: column;
}

.bar {
  width: 35%;
}
@media (max-width: 991px) {
  .bar {
    width: 100%;
    height: auto;
  }
}
</style>