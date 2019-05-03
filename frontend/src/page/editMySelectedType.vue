<template>
  <main-layout>
    <div>
      <!--<Alert></Alert>-->
      <div id="stockMySelectedItem">
        <span v-for="item in items" :key="item" class="move-item">
          <b-button
            :variant="isSelected(item.type)"
            :id="item.type"
            :data="item.name"
            @click="changedValue(item.type)"
          >
            {{ item.name }}
            <span
              @click.prevent="removeStockMySelected(item.type, item.name)"
              aria-hidden="true"
            >×</span>
          </b-button>
        </span>
      </div>

      <div>
        <div class="row">
          <div id="allSubSelectedType" class="col-6 pl-3">
            <span v-for="item in subItems" :key="item" class="move-item">
              <b-button
                :variant="isSubSelected(item.id)"
                :id="item.pid"
                :data="item.name"
                @click="changedSubValue(item.pid, item.id)"
              >
                {{ item.name }}
                <span
                  @click.prevent="removeStockMySubSelected(item.id, item.pid, item.name)"
                  aria-hidden="true"
                >×</span>
              </b-button>
            </span>
          </div>
          <div class="col-3">
            <!-- <b-btn v-b-modal.modalPrevent3 variant="info">添加</b-btn> -->
            <b-button
              v-if="subItems.length < 3 && this.currSelectedType !==''"
              variant="info"
              id="show-btn"
              @click="showModal3"
            >新增子自選股</b-button>
            <b-modal
              id="modalPrevent3"
              ref="modal3"
              title="请输入子自選股名称"
              @ok="handleOk3"
              @shown="clearName3"
            >
              <form @submit.stop.prevent="handleSubmit3">
                <b-form-input type="text" placeholder="输入子自選股名称" v-model="name3" ref="focusThis3"></b-form-input>
              </form>
            </b-modal>
          </div>
          <div class="col-3">
            <b-btn v-b-modal.modalPrevent variant="info">添加</b-btn>
            <b-btn v-if="currSelectedType != ''" variant="info" v-b-modal.modalPrevent2>修改名稱</b-btn>
            <b-btn v-if="currSelectedType != ''" variant="info" @click="save2StockMyData">保存</b-btn>
            <!-- Modal Component -->
            <b-modal
              id="modalPrevent"
              ref="modal"
              title="请输入自選股名称"
              @ok="handleOk"
              @shown="clearName"
            >
              <form @submit.stop.prevent="handleSubmit">
                <b-form-input type="text" placeholder="输入自選股名称" v-model="name" ref="focusThis"></b-form-input>
              </form>
            </b-modal>

            <!-- Modal Component -->
            <b-modal
              id="modalPrevent2"
              ref="modal2"
              title="请输入需要修改的自選股名称"
              @ok="handleOk2"
              @shown="clearName2"
            >
              <form @submit.stop.prevent="handleSubmit2">
                <b-form-input
                  type="text"
                  placeholder="请输入需要修改的自選股名称"
                  v-model="name2"
                  ref="focusThis2"
                ></b-form-input>
              </form>
            </b-modal>
          </div>
        </div>
      </div>

      <!-- List with handle -->
      <div id="listWithHandle">
        <div
          class="list-group-item float-left wd-fixed py-1 my-1"
          :key="item"
          v-for="(item, index) in list"
        >
          <span class="badge">{{index+1}}</span>
          <span class="oi oi-move" aria-hidden="true"></span>
          <autocomplete
            :ref="'autocomplete'+index"
            :source="getUrl"
            input-class="form-control"
            results-property="data"
            :results-display="formattedDisplay"
            @selected="selectedProcess"
            :initialValue="item.stockId"
            :initialDisplay="item.no +' '+item.company"
          ></autocomplete>
        </div>
        {{
        }}
        <div v-if="initNumber > list.length">
          <div
            class="list-group-item float-left wd-fixed py-1 my-1"
            :key="i"
            v-for="i in initNumber-list.length"
          >
            <span class="badge">{{i+list.length}}</span>
            <span class="oi oi-move" aria-hidden="true"></span>
            <autocomplete
              :ref="'autocomplete'+i"
              :source="getUrl"
              input-class="form-control empty-form-control"
              results-property="data"
              :results-display="formattedDisplay"
              @selected="selectedProcess"
            ></autocomplete>
          </div>
        </div>

        <div class="clearfix"></div>
      </div>
    </div>
  </main-layout>
</template>
<script>
import MainLayout from "../layouts/Main.vue";
// import Alert from '../components/alert.vue'
import Bus from "../eventBus";
import Sortable from "sortablejs";

// https://github.com/charliekassel/vuejs-autocomplete
// import Autocomplete from 'vuejs-auto-complete'
import Autocomplete from "stock-auto-complete";
// import Autocomplete from '../components/Autocomplete'
export default {
  components: {
    MainLayout,
    Autocomplete //, Alert
  },
  data() {
    return {
      name: "",
      currSelectedType: "",
      currSubSelectedType: "",
      initNumber: 50,
      options: [{ value: "", text: "Please select..." }],
      list: [],
      items: [],
      subItems: []
    };
  },
  created() {
    this.getData();
  },
  mounted() {
    let _api = this.$api;
    Sortable.create(stockMySelectedItem, {
      handle: ".move-item",
      animation: 150,
      onUpdate: function(evt) {
        var item = evt.item; // the current dragged HTMLElement
        // alert(item.outerHTML)
        let changeStockMySelectedTypeParams = [];
        $("#stockMySelectedItem button").each(function(index, data) {
          // alert($(data).attr("id")+"/"+$(data).attr("data"))
          let param = {
            type: $(data).attr("id"),
            name: $(data).attr("data")
          };
          changeStockMySelectedTypeParams.push(param);
        });
        if (changeStockMySelectedTypeParams.length > 0) {
          let url = "/api/stock/changeStockMySelectedType";
          let params = {
            changeStockMySelectedTypeDtos: changeStockMySelectedTypeParams
          };
          _api.post(url, params, rs => {
            Bus.$emit("success", "調整自選股顯示順序成功!");
          });
        }
      }
    });

    Sortable.create(listWithHandle, {
      handle: ".oi-move",
      animation: 150
    });
  },
  methods: {
    clearName() {
      this.$refs.focusThis.value = "";
      this.$refs.focusThis.focus();
    },
    handleOk(evt) {
      // Prevent modal from closing
      evt.preventDefault();
      if (!this.name) {
        alert("请输入自選股名称!");
      } else {
        this.handleSubmit();
      }
    },
    handleSubmit() {
      // this.names.push(this.name)
      this.saveData(this.name);
      this.clearName();
      this.$refs.modal.hide();
    },
    // 保存自選股名稱
    saveData(name) {
      let url = "/api/stock/saveStockMySelectedType?name=" + name;
      this.$api.post(url, null, rs => {
        this.getData();
        // Bus.$emit('reGetStockMySelectedTypes')
      });
    },

    // reame stockmydata name
    clearName2() {
      this.$refs.focusThis2.value = "";
      this.$refs.focusThis2.focus();
    },
    handleOk2(evt) {
      // Prevent modal from closing
      evt.preventDefault();
      if (!this.name2) {
        alert("请输入需要修改的自選股名称!");
      } else {
        this.handleSubmit2();
      }
    },
    handleSubmit2() {
      // this.names.push(this.name)
      this.renameStockMydataName(this.name2);
      this.clearName2();
      this.$refs.modal2.hide();
    },
    showModal3() {
      this.clearName3();
      let bts = $("#allSubSelectedType button");
      if (this.currSelectedType === "") {
        alert("請先選擇對應的子自選股名稱!");
      } else {
        if (bts.length >= 3) {
          alert("最多只能添加3個子自選股!");
        } else {
          this.$refs["modal3"].show();
        }
      }
    },
    clearName3() {
      this.$refs.focusThis3.value = "";
      this.$refs.focusThis3.focus();
    },
    handleOk3(evt) {
      // Prevent modal from closing
      evt.preventDefault();
      if (!this.name3) {
        alert("请输入子自選股名称!");
      } else {
        this.handleSubmit3();
      }
    },
    handleSubmit3() {
      // this.names.push(this.name3)
      // this.saveData(this.name3)
      // 保存子選股名稱
      let pid = this.currSelectedType;
      if (pid === "") {
        alert("請先選擇對應的自選股名稱!");
      } else {
        let url = `/api/stock/saveStockMySubSelectedType?pid=${pid}&name=${
          this.name3
        }`;
        this.$api.post(url, null, rs => {
          this.getSubData(pid);
          // Bus.$emit('reGetStockMySelectedTypes')
        });
      }
      this.clearName3();
      this.$refs.modal3.hide();
    },
    // 保存自選股名稱
    renameStockMydataName(name) {
      if (this.currSelectedType == "") {
        alert("請先選擇對應的自選股名稱!");
      } else {
        let url =
          "/api/stock/renameStockMydataName?selectedType=" +
          this.currSelectedType +
          "&name=" +
          name;
        // alert(url)
        this.$api.post(url, null, rs => {
          this.getData();
          Bus.$emit("success", "修改名稱成功!");
        });
      }
    },

    // 保存選擇的股票到自選股中
    save2StockMyData() {
      if (this.currSubSelectedType !== "") {
        // 保存子股明细
        let stockIds = [];
        $(".form-control input[type='hidden']").each(function(index, data) {
          let inputValue = $(data).val();
          if (inputValue != "") {
            stockIds.push(inputValue);
          }
        });
        // TODO: bug：当没有一条时不能删除
        if (stockIds.length > 0) {
          let url = "/api/stock/saveAllStockMySubSelected";
          let params = {
            // selectedType是子选股的类型Id
            selectedType: this.currSubSelectedType,
            stockIds: stockIds
          };
          this.$api.post(url, params, rs => {
            Bus.$emit("success", "保存成功!");
          });
        }
      } else {
        if (this.currSelectedType == "") {
          alert("請先選擇對應的自選股名稱!");
        } else {
          let stockIds = [];
          $(".form-control input[type='hidden']").each(function(index, data) {
            let inputValue = $(data).val();
            if (inputValue != "") {
              stockIds.push(inputValue);
            }
          });
          // TODO: bug：当没有一条时不能删除
          if (stockIds.length > 0) {
            let url = "/api/stock/saveAllStockMySelected";
            let params = {
              selectedType: this.currSelectedType,
              stockIds: stockIds
            };
            this.$api.post(url, params, rs => {
              Bus.$emit("success", "保存成功!");
            });
          }
        }
      }
    },
    isSelected(type) {
      // ? 'success':'warning'
      // if(this.selectedTypes != null && this.selectedTypes.indexOf(type) != -1) {
      if (this.currSelectedType == type) {
        return "warning";
      }
      return "success";
    },
    isSubSelected(id) {
      // ? 'success':'warning'
      // if(this.selectedTypes != null && this.selectedTypes.indexOf(type) != -1) {
      if (this.currSubSelectedType == id) {
        return "warning";
      }
      return "success";
    },
    getUrl(input) {
      if (this.currSubSelectedType !== "") {
        let pid = this.currSelectedType;
        return (
          "/api/stock/search4StockMyData4SubType?pid=" + pid + "&query=" + input
        );
      } else {
        return "/api/stock/search4StockMyData?query=" + input;
      }
    },
    selectedProcess(result, refs) {
      // console.log("===>"+$(this['$el']).html())
      // for(var x in this) {
      //   console.log(x+"--->"+this[x])
      // }
      $(".form-control input[type='hidden']").each(function(index, data) {
        let inputValue = $(data).val();
        // alert("value->"+result.value+"/inputValue->"+inputValue)
        if (inputValue != "" && inputValue == result.value) {
          refs.clear();
          // alert(result.display+"已經存在!")
          Bus.$emit("alerts", result.display + "已經存在!");
        }
      });
    },
    formattedDisplay(result) {
      return result.no + " " + result.company;
    },
    // 将某个股票从自选股中移除
    removeStockMySelected(selectedType, selectedName) {
      let stockId = this.$route.params.stockId;
      let $this = this;
      this.$confirm(
        "是否確定移除" +
          selectedName +
          "?, 這將會同時移除下面所有的子選股相關信息。"
      )
        .then(function() {
          // alert(stockId+"--->"+selectedType)
          let url =
            "/api/stock/removeStockMySelected?selectedType=" + selectedType;
          $this.$api.post(url, null, rs => {
            $this.getData();
            $this.currSelectedType = "";
            $this.currSubSelectedType = "";
            $this.list = [];
            $this.subItems = [];
          });
        })
        .catch(function(e) {
          // alert("Exception--->"+e)
          console.log("Exception--->" + e);
        });
    },
    // 将某个股票从子选股中移除
    removeStockMySubSelected(id, pid, selectedName) {
      let stockId = this.$route.params.stockId;
      let $this = this;
      this.$confirm("是否確定移除" + selectedName + "?")
        .then(function() {
          // alert(stockId+"--->"+selectedType)
          let url = "/api/stock/removeStockMySubSelected?id=" + id;
          $this.$api.post(url, null, rs => {
            $this.getSubData(pid);
            $this.currSubSelectedType = "";
            $this.list = [];
          });
        })
        .catch(function(e) {
          // alert("Exception--->"+e)
          console.log("Exception--->" + e);
        });
    },
    changedValue(value) {
      this.currSelectedType = value;
      this.currSubSelectedType = "";
      // 清空手动输入的内容
      $(".empty-form-control input").val("");
      this.getData();
      this.getSubData(value);
      this.getMyStockMySelected(value);
    },
    changedSubValue(pid, id) {
      this.currSubSelectedType = id;
      // 清空手动输入的内容
      $(".empty-form-control input").val("");
      this.getSubData(pid);
      this.getMyStockMySubSelected(id);
    },
    getMyStockMySelected(type) {
      this.list = [];
      if (type != "") {
        this.$api.get("/api/stock/getStockMyDatasByType/" + type, null, rs => {
          this.list = rs;
        });
      }
    },
    getMyStockMySubSelected(id) {
      this.list = [];
      if (id != "") {
        this.$api.get("/api/stock/getStockMyDatasBySubId/" + id, null, rs => {
          this.list = rs;
        });
      }
    },
    getData() {
      // this.options = []
      let url = "/api/stock/getStockMySelectedTypes";
      this.$api.get(url, null, rs => {
        this.items = rs;
        /*for (var i = 0; i < rs.length; i++) {
          this.options.push({
            value: rs[i].type, text: rs[i].name
        }*/
      });
    },
    getSubData(pid) {
      // this.options = []
      if (pid == "") {
        alert("請先選擇對應的自選股名稱!");
      } else {
        let url = `/api/stock/getStockMySubSelectedTypes?pid=${pid}`;
        this.$api.get(url, null, rs => {
          this.subItems = rs;
        });
      }
    }
  }
};
</script>

<style scoped>
div {
  padding: 1px;
}

div button {
  margin: 5px;
}
.autocomplete {
  float: right;
  width: 140px;
  padding: 1px;
}

.wd-fixed {
  width: 200px;
}
</style>

<!-- <style lang="scss">
  @import "../style/style";

</style> -->
