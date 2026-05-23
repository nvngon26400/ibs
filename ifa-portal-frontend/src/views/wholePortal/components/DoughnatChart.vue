<script>
import { Doughnut } from 'vue3-chart-v2'

export default ({
  name: 'PieChart',
  extends: Doughnut,
  props: {
    chartDataForm: { type: Object, require: true, default: () => ({}) },
    isPrint: { type: Boolean, require: false, default: false }
  },
  data() {
    return {
      datas: {
        // 凡例とツールチップに表示するラベル
        labels: [],
        // 表示するデータ
        datasets: [
          {
            data: [],
            backgroundColor: ['#f87979', '#aa4c8f', '#38b48b', '#006e54', '#c1e4e9', '#89c3eb', '#c3d825', '#f0e68c'],
            borderColor: 'transparent'
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        devicePixelRatio: this.isPrint ? 4 : 1,
        layout: {
          padding: {
            left: 0,
            right: 0,
            top: 0,
            bottom: 0
          }
        },
        legend: {
          position: 'right',
          labels: {
            fontSize: this.isPrint ? 6 : 12,
            padding: this.isPrint ? 5 : 10,
            generateLabels: function(data) {
              // 凡例の表示
              const chartData = data.tooltip._data
              const chartDataset = chartData.datasets[0]
              const chartLabels = chartData.labels
              let total = 0 // 合計格納

              const legendArray = []

              // 全データの合計算出
              for (let i = 0; i < chartLabels.length; i++) {
                const eachData = chartDataset.data[i]
                total = total + eachData
              }

              for (let i = 0; i < chartLabels.length; i++) {
                const eachLabel = chartLabels[i]
                const eachData = chartDataset.data[i]
                const backgroundColor = chartDataset.backgroundColor[i]
                const ratioValue = isNaN(eachData / total * 100) ? '-' : (eachData / total * 100).toFixed(2)
                const eachLengend = {
                  // 表示されるラベル
                  text: `${eachLabel}(${ratioValue}%)`,
                  fillStyle: backgroundColor,
                  hidden: false,
                  lineCap: 'square',
                  lineDash: [0],
                  lineDashOffset: 0,
                  lineJoin: 'bevel',
                  lineWidth: 0,
                  strokeStyle: '',
                  pointStyle: ''
                }

                legendArray.push(eachLengend)
              } // end for

              return legendArray
            }
          }
        },
        tooltips: {
          callbacks: {
            label: function(tooltipItem, data) {
              let total = 0 // 合計格納
              const indexItem = data.datasets[0].data[tooltipItem.index] // マウスを当てたデータ
              // 全データの合計算出
              data.datasets[0].data.forEach(item => {
                total += item
              })
              const ratioValue = isNaN(indexItem / total * 100) ? '-' : (indexItem / total * 100).toFixed(2)
              // パーセント表示
              return data.labels[tooltipItem.index] + '(' + ratioValue + '%)'
            }
          }
        }
      }
    }
  },
  mounted() {
    this.updateData()
    this.$nextTick(() => {
      this.renderChart(this.datas, this.options)
    })
  },
  methods: {
    getColorList(dataList) {
      const colorList = this.datas.datasets[0].backgroundColor
      let i = dataList.length
      while (colorList.length < dataList.length) {
        const findIndex = this.datas.datasets[0].backgroundColor.findIndex(color => color === `#${this.genColor(i)}`)
        if (findIndex === -1) {
          // グラフのカラーを追加
          colorList.push((`#${this.genColor(i)}`))
          i++
        }
      }
      return colorList
    },
    // hex-colorを生成
    genColor(seed) {
      let color = Math.floor((Math.abs(Math.sin(seed) * 16777215)))
      color = color.toString(16)
      while (color.length < 6) {
        color = '0' + color
      }
      return color
    },
    updateData() {
      this.datas.labels = this.chartDataForm.portfolioSummaryList.map(item => this.$_out(item.securityClass))
      this.datas.datasets[0].data = this.chartDataForm.portfolioSummaryList.map(
        item => Number(this.$_withCommaZeroPadding(item.byProductAssetsRatio, 2)))
      this.datas.datasets[0].backgroundColor = this.getColorList(this.datas.datasets[0].data)
    }

  }
})
</script>
