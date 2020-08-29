const vm = new Vue({
  el: '#app',
  created: function(){

    let array = ['#10aec2','#b0d5df','#bc84a8','#8fb2c9','#eea6b7','#7a7374','#eea2a4'];
    this.mainBackColors = array;
    let color = array[Math.floor(Math.random()*(array.length))];
    console.info('当前背景颜色色调为:',color);
    this.mainBackColor = color;

  },
  data: function () {
    return {
      mainBackColors:null,
      mainBackColor: null,//默认颜色
      message: '你好啊',
      options: [{
        value: '1',
        label: '第一版',
        text: '2017年3月'
      }, {
        value: '2',
        label: '第二版',
        text: '2017年6月'
      }, {
        value: '3',
        label: '第三版',
        text: '2018年4月'
      }, {
        value: '4',
        label: '第四版',
        text: '2019年3月'
      }],
      selectValue: '4'

    }
  },
  methods: {
    scanBackColor(){
      let color = this.mainBackColors[Math.floor(Math.random()*(this.mainBackColors.length))];

      this.mainBackColor = color;
      console.info('当前背景颜色色调为:',color);

    },

  }

})