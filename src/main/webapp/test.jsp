<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2019/12/26
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>饼图</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <script src="js/echarts.js"></script>
    <script>
        function init() {

            var myChart = echarts.init(document.getElementById("main"));
            var m2R2Data = [
                {"value":"150000","accountNo":"656465165151615155","cstNo":"1000063212","category":"1","categoryName":"余额"},
                {"value":"20000","accountNo":"62285601001671974","cstNo":"1000063212","category":"1","categoryName":"理财"},
                {"value":"12000","accountNo":"6564651658578572435","cstNo":"1000063212","category":"1","categoryName":"基金"},
                {"value":"23000","accountNo":"6564651612435572435","cstNo":"1000063212","category":"1","categoryName":"黄金"},
                {"value":"54000","accountNo":"6564663456685792435","cstNo":"1000063212","category":"2","categoryName":"信用卡"},
                {"value":"1000000","accountNo":"6535635463563456345","cstNo":"1000063212","category":"2","categoryName":"贷款"}
            ];

            var licai = new Array();
            var total1 = 0;
            var m = 0;
            var fuzhai = new Array();
            var total2 = 0;
            var n = 0;
            for (var i = 0; i < m2R2Data.length; i++) {
                if (m2R2Data[i].category=='1') {
                    licai[m++] = m2R2Data[i];
                    total1 += Number(m2R2Data[i].value);
                } else {
                    fuzhai[n++] = m2R2Data[i];
                    total2 += Number(m2R2Data[i].value);
                }
            }

            var option = {
                title: [
                    {
                        text: '理财',
                        textStyle: {
                            fontSize: 16,
                            color: "black"
                        },
                        left: "10%"
                    },
                    {
                        text: '合计',
                        subtext: total1,
                        textStyle:{
                            fontSize:20,
                            color:"black"
                        },
                        subtextStyle: {
                            fontSize: 20,
                            color: 'black'
                        },
                        textAlign:"center",
                        x: '34.5%',
                        y: '44%',
                    },{
                        text: '负债',
                        textStyle: {
                            fontSize: 16,
                            color: "black"
                        },
                        left: "50%"
                    },
                    {
                        text: '合计',
                        subtext: total2,
                        textStyle:{
                            fontSize:20,
                            color:"black"
                        },
                        subtextStyle: {
                            fontSize: 20,
                            color: 'black'
                        },
                        textAlign:"center",
                        x: '70%',
                        y: '44%',
                    }],
                tooltip: {
                    type: 'pie',
                    trigger: 'item',
                    formatter: function (parms) {
                        var str = parms.marker + "" + parms.data.categoryName + "</br>" +
                            "金额：" + parms.data.value + "</br>" +
                            "占比：" + parms.percent + "%";
                        return str;
                    }
                },
                legend: {
                    type: "scroll",
                    orient: 'vertical',
                    left: '70%',
                    align: 'left',
                    top: 'middle',
                    textStyle: {
                        "color": '#8C8C8C'
                    },
                    height: 250
                },
                series: [
                    {
                        type: 'pie',
                        center: ['35%', '50%'],
                        radius: ['40%', '65%'],
                        clockwise: false, //饼图的扇区是否是顺时针排布
                        label: {
                            normal: {
                                show: true,
                                position: 'outter',
                                formatter: function (parms) {
                                    return parms.data.categoryName
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                length: 5,
                                length2: 3,
                                smooth: true
                            }
                        },
                        data: licai
                    },
                    {
                        type: 'pie',
                        center: ['70%', '50%'],
                        radius: ['40%', '65%'],
                        clockwise: false, //饼图的扇区是否是顺时针排布
                        label: {
                            normal: {
                                show: true,
                                position: 'outter',
                                formatter: function (parms) {
                                    return parms.data.categoryName
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                length: 5,
                                length2: 3,
                                smooth: true
                            }
                        },
                        data: fuzhai
                    }
                ]
            };
            myChart.setOption(option);
        }
    </script>
</head>
<body onload="init()">
<div id="main" style="width: 100%;height:400px;"></div>
</body>
</html>
