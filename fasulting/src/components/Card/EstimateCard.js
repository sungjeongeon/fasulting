import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import styles from "./EstimateCard.module.css"

function EstimateCard() {
  const estimate = {
    ps_name: '더성형외과의원',
    parts: [
      '눈매교정',
      '앞트임',
      '밑트임'
    ],
    opinion: "인상이 더 뚜렷해보여, 눈매교정과 트임 시술 모두 추천드립니다.",
    price: 250
  }
  
  return (
    <Card sx={{ maxWidth: 330, marginLeft: '4rem', marginTop: '2.5rem' }}>
      <CardContent sx={{ width: '92%', margin: 'auto'}}>
        <Typography variant="h5" component="div" fontWeight={"bold"} sx={{ marginTop: '0.7rem'}}>
          {estimate.ps_name}
        </Typography>
        <Typography color="text.secondary" fontWeight={"bold"} sx={{ marginTop: '2rem'}} fontSize={"1.1rem"}>
          상담 부위
        </Typography>
        <div className={styles.my}>
        {estimate.parts.map((part, index) => {
          return (
            <span
            key={index}
            className={styles.parts}
            >{part}</span>
            )
          })}
        </div>
        <Typography color="text.secondary" fontWeight={"bold"} sx={{ marginTop: '2rem'}} fontSize={"1.1rem"}>
          상담 소견
        </Typography>
        <p className={`${styles.opinion} ${styles.my}`}>{estimate.opinion}</p>
        <Typography color="text.secondary" fontWeight={"bold"} sx={{ marginTop: '2rem'}} fontSize={"1.1rem"}>
          예상 견적
        </Typography>
        <p className={`${styles.price} ${styles.my}`}>{estimate.price}만원</p>
      </CardContent>
    </Card>
  );
}

export default EstimateCard;