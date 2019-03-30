<template>
    <div class='outerDiv' @click='clickInto'>
        <Card class="fundCardBody" dis-hover>
            <div class='title'>{{title}}</div>
            <div class='oneLine'>
                <span class='num' :class='{textThrough:isShow}'><img :src="image"></span>
                
                <!-- <span class="unit">台件</span> -->
            </div>
            <div class='twoLine'>
                <span class='bigNum' :class='{textThrough:isShow}'>{{value}}次</span>
            </div>
            <div class='clickInto' :class='{noCanUse:isShow,canUse:!isShow}'>点击进入</div>
        </Card>
        <div class="logoLine"></div>      
    </div>
</template>
<script>
export default {
    name: 'iconCard',
    props:{
        iconData:{
            type:Array
        }
    },
    data() {
        return {
            title:'',
            image:'',
            num:0,
            value:0,
            isShow:false
        }
    },
    methods:{
        clickInto(){
            window.open(this.iconData.url,'_blank');
            // switch(this.type){
            //     case 'manageFund': this.$router.push('/UnitAssets'); break;
            //     case 'preEnterFund': this.$router.push('/portalyurk'); break;
            //     case 'noEnterFund': this.$Message.info('暂不支持此功能'); break;
            //     case 'waitDealFund': this.$router.push('/WaitDealAsset'); break;
            //     default: break;
            // }
        },
        getData(){
          this.title = this.iconData.title
          this.image = this.iconData.image
          this.getManageFundData();
            // switch(this.type){
            //     case 'manageFund': this.title='管辖资产'; this.getManageFundData(); break;
            //     case 'preEnterFund': this.title='预入库资产'; this.getPreEnterFundData(); break;
            //     case 'noEnterFund': this.title='采购未入库资产'; this.getNoEnterFundData(); break;
            //     case 'waitDealFund': this.title='待处置资产'; this.getWaitDealFundData(); break;
            //     default: break;
            // }
        },
        getManageFundData(){
            this.$http.post(this.rootUrl+'cardStatistics/pliceAssets').then(
                function(response) {
                    if(response.status==200||response.statusText=='OK'){
                        this.num=response.data[0].shul;
                        this.value=response.data[0].jiaz
                    }
                }.bind(this)
            )
            .catch(
                function(error) {
                    
                }.bind(this)
            );
        },
        getPreEnterFundData(){
            this.$http.post(this.rootUrl+'/cardStatistics/expectedStorage').then(
                function(response) {
                    if(response.status==200||response.statusText=='OK'){
                        this.num=response.data[0].shul;
                        this.value=response.data[0].jiaz
                    }
                }.bind(this)
            )
            .catch(
                function(error) {
                    
                }.bind(this)
            );
        },
        getNoEnterFundData(){
            this.$http.post(this.rootUrl+'/cardStatistics/purchaseUnExpectedStorage').then(
                function(response) {
                    if(response.status==200||response.statusText=='OK'){
                        this.num=response.data[0].shul;
                        this.value=response.data[0].jiaz
                    }
                }.bind(this)
            )
            .catch(
                function(error) {
                    
                }.bind(this)
            );
        },
        getWaitDealFundData(){
            this.$http.post(this.rootUrl+'/cardStatistics/pendingDisposal').then(
                function(response) {
                    if(response.status==200||response.statusText=='OK'){
                        this.num=response.data[0].shul;
                        this.value=response.data[0].jiaz
                    }
                }.bind(this)
            )
            .catch(
                function(error) {
                    
                }.bind(this)
            );
        },
        toMoneyStr(moneystamp){
            moneystamp=moneystamp.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
            return moneystamp+=".00";
        }
    },
    created(){
        this.getData();
    },
    mounted(){
        $('.fundCardBody').mouseenter(function(){
            $(this).children("div:nth-child(1)").children("div:nth-child(2)").addClass("textUp")
            $(this).children("div:nth-child(1)").children("div:nth-child(2)").removeClass("textDown")
            $(this).children("div:nth-child(1)").children("div:nth-child(3)").hide(0)
            $(this).children("div:nth-child(1)").children("div:last-child").fadeIn(200)
        });
        $('.fundCardBody').mouseleave(function(){
            $(this).children("div:nth-child(1)").children("div:nth-child(2)").addClass("textDown")
            $(this).children("div:nth-child(1)").children("div:nth-child(2)").removeClass("textUp")
            $(this).children("div:nth-child(1)").children("div:nth-child(3)").show(0)
            $(this).children("div:nth-child(1)").children("div:last-child").fadeOut(0)
        });

        if(this.type=='noEnterFund'){
            this.isShow=true;
        }
    }
}
</script>
<style scoped lang='less'>
.outerDiv{
    position: relative;
    .logoLine{
        position: absolute;
        width:2px;
        height:17px;
        background: #3477CB;
        top:19px;
        left:0px;
    }
}
.fundCardBody{
    width:100%;
    height:182px;
    background: #fff;
    border: 1px solid #E8E8E8;
    margin-right:10px;
    border-radius:2px;
    /deep/.ivu-card-body{
        padding:0!important;
    }
    .title{
        font-family: MicrosoftYaHei;
        color: #999999;
        padding-left:20px;
        margin-top: 16px;
        font-size: 14px;
        text-align: left;
    }
    .oneLine{
        text-align: center;
        padding-top:34px;
        padding-bottom:5px;

        .num{
            font-family: MicrosoftYaHei-Bold;
            font-size: 30px;
            color: #444444;
            text-align: center;
        }
        .unit{
            font-family: MicrosoftYaHei;
            font-size: 14px;
            color: #444444;
            letter-spacing: 0;
            text-align: left;
        }
        
    }
    .twoLine{
        text-align: center;
        .bigNum{
            font-family: MicrosoftYaHei;
            font-size: 14px;
            color: #999999;
            text-align: center;
        }
    }
    .clickInto{
        width:96px;
        height:32px;
        line-height:32px;
        border-radius: 2px;
        text-align: center;
        color:#fff;
        font-family: MicrosoftYaHei;
        font-size: 14px;
        display: none;
        cursor: pointer;
        margin:0 auto;
    }
    .textThrough{
        text-decoration: line-through;
    }
    .canUse{
        background-image: linear-gradient(45deg, #3477CB 0%, #5A9CF0 100%);
    }
    .noCanUse{
        background-image: linear-gradient(0deg, #a8a8a8 0%, #a8a8a8 100%);
    }
}
.textUp{
    animation: textUp 0.2s linear 0s alternate  forwards;
}
@keyframes textUp{
    from{padding-top:34px;}
    to{padding-top:24px; }
}
.textDown{
    animation: textDown 0.2s linear 0s alternate  forwards;
}
@keyframes textDown{
    from{padding-top:24px}
    to{padding-top:34px}
}
</style>
