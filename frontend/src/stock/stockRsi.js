// https://github.com/apache/incubator-echarts/issues/6583
// http://gallery.echartsjs.com/editor.html?c=candlestick-sh
// https://github.com/anandanand84/technicalindicators/tree/v1.1.13

import * as stockUtils from '../utils/stockUtils'
import * as dateUtils from '../utils/dateUtils'

let RSI = require('technicalindicators').RSI

export default function getData(datasets, kineType) {
    let kDisplay = kineType == 1 ? '月' : '日'
    let datas = stockUtils.splitData(datasets)
    let inputRSI12 = {
        values: stockUtils.getCloses(datas),
        period: 12
    }

    let rsi12_src = RSI.calculate(inputRSI12)
    let rsi12 = stockUtils.getSlice(rsi12_src)

    let inputRSI30 = {
        values: stockUtils.getCloses(datas),
        period: 30
    }

    let rsi30_src = RSI.calculate(inputRSI30)
    let rsi30 = stockUtils.getSlice(rsi30_src)

    let inputRSI60 = {
        values: stockUtils.getCloses(datas),
        period: 60
    }

    let rsi60_src = RSI.calculate(inputRSI60)
    let rsi60 = stockUtils.getSlice(rsi60_src)

    return {
        // backgroundColor: '#21202D',
        // color: '#fff',
        /* title: {
            text: kDisplay+'k线'
        }, */
        // backgroundColor: '#21202D',
        // 提示框浮层的位置
        animation: false,
        /* tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        }, */
        tooltip: {
            trigger: 'axis',
            backgroundColor: 'black',
            position: [0, 0],
            // extraCssText:'width:100px;height:60px;',
            formatter: function(params) {
                let v = `<font color="${STOCK_CONFIG.col.rsi12}">RSI-12: ${params[0].value.toFixed(1)}</font>
                <font color="${STOCK_CONFIG.col.rsi30}">RSI-30: ${params[1].value.toFixed(1)}</font>
                <font color="${STOCK_CONFIG.col.rsi60}">RSI-60: ${params[2].value.toFixed(1)}</font>`
                $('#tooltipId3' + kineType).html(v)
                return ''
            },
            axisPointer: {
                type: 'cross',
                label: {
                    show: false,
                    color: '#ff0'
                },
                crossStyle: {
                    // color: '#1e90ff',
                    width: 1,
                    type: 'solid'
                }
            }
        },
        grid: [
            {
                top: '4%',
                left: '2%',
                right: '6%',
                height: '90%'
            }
        ],
        // 坐标轴指示器（axisPointer）的全局公用设置
        axisPointer: {
            link: {
                // 所有x坐标一起联动
                xAxisIndex: 'all'
            },
            // mouse动时坐标处的文字
            label: {
                backgroundColor: '#777'
            }
            // triggerOn:'click'
        },
        // 上下两个图表的x轴数据
        xAxis: [
            {
                type: 'category',
                data: stockUtils.getSlice(datas.categoryData),
                // 坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。
                boundaryGap: true,
                // 坐标文字内容
                /* axisLabel: {
                onZero: false,
                // 坐标文字相关样式
                textStyle: {
                    fontSize: '12px',
                    color: 'green'
                } ,
                formatter: function (value) {
                    return dateUtils.formatTime('MM/dd', value)
                }
            } */
                // 坐标刻度
                axisTick: {
                    show: false
                },
                // 坐标文字内容
                axisLabel: {
                    show: false
                }
            }
        ],
        //
        yAxis: [
            {
                // position: 'left',
                scale: true,
                axisLabel: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                // 坐标刻度
                axisTick: {
                    show: false
                }
            },
            {
                position: 'right',
                axisLabel: {
                    show: false,
                    // margin:-18,
                    lineStyle: {
                        color: 'red'
                    },
                    color: STOCK_CONFIG.col.y
                },
                scale: true,
                // position: 'right',,
                min: 0,
                max: 100,
                splitNumber: 2,
                // splitArea: {
                //     show: false
                // },
                splitLine: {
                    show: false,
                    lineStyle: {
                        color: ['#888'],
                        type: 'dotted'
                    }
                },
                // 坐标刻度
                axisTick: {
                    show: false
                }
            }
        ],
        dataZoom: [
            {
                type: 'inside',
                disabled: true,
                start: STOCK_CONFIG.st,
                end: STOCK_CONFIG.ed
            },
            {
                show: false,
                type: 'slider',
                // y: '94%',
                start: STOCK_CONFIG.st,
                end: STOCK_CONFIG.ed
            }
        ],
        series: [
            {
                name: 'RSI-12',
                type: 'line',
                data: rsi12,
                smooth: true,
                showSymbol: false,
                symbol: 'none',
                lineStyle: {
                    normal: {
                        width: 2,
                        color: STOCK_CONFIG.col.rsi12
                    }
                }
            },
            {
                name: 'RSI-30',
                type: 'line',
                data: rsi30,
                smooth: true,
                showSymbol: false,
                symbol: 'none',
                lineStyle: {
                    normal: {
                        width: 2,
                        color: STOCK_CONFIG.col.rsi30
                    }
                }
            },
            {
                name: 'RSI-60',
                type: 'line',
                data: rsi60,
                smooth: true,
                showSymbol: false,
                symbol: 'none',
                lineStyle: {
                    normal: {
                        width: 2,
                        color: STOCK_CONFIG.col.rsi60
                    }
                }
            }
        ]
    }
}
