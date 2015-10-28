$(function () {
    $('#year').highcharts({
        title: {
            text: 'Average number of incidents per year',
            x: -20 //center
        },
        subtitle: {
            text: 'Source: https://data.sfgov.org',
            x: -20
        },
        yAxis: {
            title: {
                text: 'Year'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },

        series: [{
            name: 'San Francisco incidents',
            data: year
        }]
    });
});

$(function () {
    $('#month').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Average number of incidents per month',
            x: -20 //center
        },
        subtitle: {
            text: 'Source: https://data.sfgov.org',
            x: -20
        },
        xAxis: {
            categories: [
                'Jan',
                'Feb',
                'Mar',
                'Apr',
                'May',
                'Jun',
                'Jul',
                'Aug',
                'Sep',
                'Oct',
                'Nov',
                'Dec'
            ],
            crosshair: true
        },
        yAxis: {
            min: 100000,
            title: {
                text: 'Number of incidents'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Incidents in San Francisco',
            data: month

        }]
    });
});

$(function () {
    $('#day').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Average number of incidents per day of the week',
            x: -20 //center
        },
        subtitle: {
            text: 'Source: https://data.sfgov.org',
            x: -20
        },
        xAxis: {
            categories: [
                'Friday',
                'Monday',
                'Saturday',
                'Sunday',
                'Thursday',
                'Tuesday',
                'Wednesday'
            ],
            crosshair: true
        },
        yAxis: {
            min: 200000,
            title: {
                text: 'Number of incidents'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Incidents in San Francisco',
            data: day

        }]
    });
});

$(function () {
    $('#time').highcharts({
        title: {
            text: 'Average number of incidents per time',
            x: -20 //center
        },
        subtitle: {
            text: 'Source: https://data.sfgov.org',
            x: -20
        },
        yAxis: {
            title: {
                text: 'Time'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },

        series: [{
            name: 'San Francisco incidents',
            data: time
        }]
    });
});
