/**
 * 특정 요일의 주를 구하는 함수
 */
function getWeekOfMonth(date) {
  
  const year = date.getFullYear();
  const month = date.getMonth()+1;
  const today = date.getDate();
  
  // 1. 해당 월의 1일과 마지막 일의 요일 및 날짜 계산
  const firstDate = new Date(year, month-1, 1);
  const firstDay = firstDate.getDay() === 0 ? 7 : firstDate.getDay();
  const firstMondayOfMonth = firstDay === 1 ? 1 : firstDate.getDate() + (7-firstDay+1);  // 첫 월요일 날짜
  
  const lastDate = new Date(year, month, 0).getDate();
  const lastDay = new Date(year, month-1, lastDate).getDay() === 0 ? 7 : new Date(year, month-1, lastDate).getDay();
  const lastMondayOfMonth = lastDate-lastDay+1;

  // 2. 경우의 수에 따른 특정 날짜의 주 계산
  let weekOfToday = Math.floor((today-firstMondayOfMonth) / 7) + 1; // default : 1일이 속한 주가 첫 주가 아닌 경우
  
  if (firstDay <= 4) {
    // 1일이 월~목요일에 존재 : 1일 있는 주가 첫 주인 경우
    if(firstMondayOfMonth !== 1) weekOfToday += 1; // 1일이 속한 주가 첫 주이지만 1일이 첫 번째 월요일이 아닌 경우 : 첫 번째 월요일이 속한 주가 두 번째 주가 되므로 +1
  } 
  
  return weekOfToday;
}

