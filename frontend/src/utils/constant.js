// 分页显示15条
window.PAGE_SIZE = 15
// K线图获取最近xx天的历史数据
window.RECENT_DATE = -88

window.STOCK_CONFIG = {
    // barCategoryGap则用于设置不同类目之间的间距
    barCategoryGap: '5px',
    barWidth: '80%', //指定柱宽度
    col: {
        // up: 'rgb(153, 14, 14)',
        // down: '#19b34c',
        // k
        up: '#FF0033',
        down: '#0000FF', // 蓝
        m5: '#FFFF00',
        m10: '#9400D3',
        m20: '#228B22',
        m60: '#FF7500',
        boll: '#228B22',
        // vol
        volup: 'red', // red
        voldown: '#30d94c',
        // rsi
        rsi12: '#FF0033',
        rsi30: '#9400D3',
        rsi60: '#0000FF',
        // tower
        towerUp: '#FF0033',
        towerDown: '#0000FF',
        // dmi
        diUp: '#FF0033',
        diDown: '#0000FF',
        adx: '#9400D3',
        adxr: '#FF8C00',
        xr: '#FF00FF',
        // macd
        oscup: '#FF0033', // red
        oscdown: '#00CD00', // green
        dif: '#9400D3',
        macd: '#0000FF'
        // y: '#ffefef'
    },
    // bg: '#000',
    st: 41,
    ed: 100
}
