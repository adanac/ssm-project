//存放主要交互逻辑的js代码
// javascript 模块化(package.类.方法)/**
// console.log('rootPath:'+rootPath);
var $basePath = $("#basePath").val();
console.log($basePath);
var seckill = {
    //封装秒杀相关ajax的url
    URL: {
        now: function () {
            return $basePath + '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return $basePath + '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return $basePath + '/seckill/' + seckillId + '/' + md5 + '/execution';
        }

    },

    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;//直接判断对象是否为空,空就是undefined 就是false, isNaN 非数字返回true
        } else {
            return false;
        }
    },

    //详情页秒杀逻辑
    detail: {
        //详情页初邕始化
        init: function (params) {
            //手机验证和登录,计时交互
            //在cookie中查找手机号
            var userPhone = $.cookie('userPhone');

            //模拟登录
            if (!seckill.validatePhone(userPhone)) {
                //绑定手机,控制输出
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                     show:true, //显示弹出层
                     backdrop:'static', //禁止位置关闭
                     keyboard:false //关闭键盘事件
                 })

                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log('inputPhone=' + inputPhone);
                    if (seckill.validatePhone(inputPhone)) {
                        //电话写入cookie(7天过期)
                        $.cookie("killPhone", inputPhone, {expires: 7, path: '/seckill'});
                        //验证通过,刷新页面
                        window.location.reload();
                    } else {
                        //错误文案信息抽取到前端字典里
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }

                });
            }


            //已经登录
            //计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            //获取服务器当前时间
            console.log('___now url:' + seckill.URL.now());
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    //时间判断 交互设计
                    seckill.countDown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result:' + result);
                }
            });
        }
    },

    handlerSeckill: function (seckillId, node) {
        //获取秒杀地址,控制显示逻辑,执行秒杀
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');

        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            //在回调函数中执行交互流程
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer && exposer['exposed']) {
                    //开启秒杀,获取秒杀地址
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log('killUrl:' + killUrl);
                    //绑定一次事件
                    $('#killBtn').one('click', function () {
                        //执行秒杀请求
                        //1.先禁用按钮
                        $(this).addClass('disabled');
                        //2.发送秒杀请求,执行秒杀
                        $.post(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                //显示秒杀结果
                                node.html('<span class="label label-sucess">' + stateInfo + '</span>')
                            }

                        });
                    });
                    node.show();
                } else {
                    //未开启秒杀(浏览器计时偏差)
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    console.log('now:' + now);
                    seckill.countDown(seckillId, now, start, end);
                }
            } else {
                console.log('result:' + result);
            }
        });
    },

    countDown: function (seckillId, now, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        if (now > endTime) {
            seckillBox.html('秒杀已结束');
        } else if (now < startTime) {
            //秒杀未开始,计时事件绑定
            var killTime = new Date(startTime + 1000);//防止用户时间偏移
            seckillBox.countdown(killTime, function (event) {
                //时间格式
                var format = event.strftime('秒杀倒计时:%D天 %H时 %M分 %S秒 ');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                //时间完成后回调事件
                //获取秒杀地址,控制显示逻辑,执行秒杀
                console.log('_________finish countdown');
                seckill.handlerSeckill(seckillId, seckillBox);
            });
        } else {
            //秒杀开始
            seckill.handlerSeckill(seckillId, seckillBox);
        }
    }
}