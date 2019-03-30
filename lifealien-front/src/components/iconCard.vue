<template>
    <div class='outerDiv' @click='clickInto'>
        <Card class="fundCardBody" dis-hover>
            <div class='title'>{{title}}</div>
            <div class='oneLine'>
                <span class='num' :class='{textThrough:isShow}'><img :src="image"></span>
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
            type:Object
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
            if("add" === this.iconData.url){
                this.add();
            }else{
                window.open(this.iconData.url,'_blank');
            }
        },
        add(){
            this.$Modal.confirm({
                title: 'Title',
                content: '<p>Content of dialog</p><p>Content of dialog</p>',
                onOk: () => {
                    this.$Message.info('Clicked ok');
                },
                onCancel: () => {
                    this.$Message.info('Clicked cancel');
                }
            });
        },
        getData(){
          this.title = this.iconData.title
          this.image = this.iconData.image
        }
    },
    created(){
        this.getData();
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
.vertical-center-modal{
    display: flex;
    align-items: center;
    justify-content: center;

    .ivu-modal{
        top: 0;
    }
}
</style>
