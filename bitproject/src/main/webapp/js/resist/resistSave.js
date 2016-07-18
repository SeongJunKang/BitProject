$(function() {
   $("#savebtn").click(function() {
      if ($("#checknick").attr("data-state") == "no") {
         window.alert("닉네임 체크를 클릭해주세요.")
      } else {
          var checklist="";
            $("input[name=box]:checked").each(function() {
              checklist += $(this).val()+",";
            });
          checklist = checklist.substring(0,checklist.length-1);
          var radian = 0;
          if ($("#radian").val() != "") {
            radian = $("#radian").val();
          }

          $.post("../ajax/petsitter/add.do", {
              nick : $("#nickname").val(),
              amt :$("#price").val(),        
              ktalk : $("#kakao").val(),
              region : $("#region").val(),
              bank : $("#bank").val(),
              bknm: $("#holder").val(),
              acc : $("#account").val(),
              ser : checklist,
              inqur : $("#inqur1").val() + "시 ~ "
                     +$("#inqur2").val()+ "시",
              pet :  $( "#animal").val(),
              addr_1 :$("#addr1").val(),
              addr_2 :$("#addr2").val(),
              addr_3 :$("#addr3").val(),
              rad : radian,
              lat : sessionStorage.getItem("lat"),
              lnt : sessionStorage.getItem("lnt"),
              intro :$("#intro").val(),
              hospital : $("#hospital").val()
            }, function(result) {
              if (result.status == "success") {
                 sessionStorage.removeItem("lat");
                 sessionStorage.removeItem("lnt");
                location.href = "../main/index.html";
              } else {
                window.alert("등록 실패입니다.");
              }
            }, "json");
          $.post("../ajax/member/upgrade.do?", {
               gra :  2
             }, function(result) {
               if (result.status == "success") {
                  location.href = "../main/index.html";
                } else {
                  window.alert("등록 실패입니다.");
                }
           }, "json");
      }
   });
})