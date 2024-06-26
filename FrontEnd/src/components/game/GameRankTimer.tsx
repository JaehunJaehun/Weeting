import { useState, useEffect } from 'react';
import styles from '@/styles/game/GameWordSetting.module.css';
import timerRank from '@/assets/images/timerRank.png';
import { RoomInfo, MessageScore } from '@/types/game';
import { userState } from '@/recoil/atom';
import { useRecoilValue, useSetRecoilState } from 'recoil';

const GameRankTimer = ({ roomInfo, messageScore }: { roomInfo: RoomInfo; messageScore: MessageScore }) => {
  const endTime: string | null = roomInfo.roomEndTime;
  const forbiddenTime: string | null = roomInfo.roomForbiddenTime;
  const [endTimeLeft, setEndTimeLeft] = useState('');
  const [forbiddenTimeLeft, setForbiddenTimeLeft] = useState('');
  const userInfo = useRecoilValue(userState);

  useEffect(() => {
    if (endTime != null) {
      setEndTimeLeft(((new Date(endTime).getTime() - new Date().getTime()) / 1000).toFixed(0).toString());
    }
    if (forbiddenTime != null) {
      setForbiddenTimeLeft(((new Date(forbiddenTime).getTime() - new Date().getTime()) / 1000).toFixed(0).toString());
    }
  }, [roomInfo]);

  useEffect(() => {
    if (roomInfo.roomStatus === 'wordsetting') {
      const timerId = setInterval(() => {
        setForbiddenTimeLeft(
          ((new Date(forbiddenTime!).getTime() - new Date().getTime()) / 1000).toFixed(0).toString(),
        );
      }, 1000);

      setTimeout(() => {
        clearInterval(timerId);
        setForbiddenTimeLeft('0');
        // status가 변경 될... 거지? 백에서?
      }, 15000);
    } else if (roomInfo.roomStatus === 'start') {
      const timerId = setInterval(() => {
        setEndTimeLeft(((new Date(endTime!).getTime() - new Date().getTime()) / 1000).toFixed(0).toString());
      }, 1000);

      setTimeout(() => {
        clearInterval(timerId);
        setEndTimeLeft('0');
        // status 변경 websocket?? publish? send?
      }, 120000);
    } else {
    }
  }, [roomInfo]);

  return (
    <div className={styles.RanktimerContainer}>
      <div className={styles.Ranktimers}>
        {userInfo.nickname === messageScore.nickname && (
          <div className={styles.PersonalScore}>
            <p>현재 점수 : ??</p>

            {/* <p>현재 점수 : {messageScore.highest_similarity}</p> */}
          </div>
        )}
        <p className={styles.RanktimerText}>
          {roomInfo.roomStatus === 'wordsetting'
            ? forbiddenTimeLeft
            : roomInfo.roomStatus === 'start'
              ? endTimeLeft
              : '0'}
          초
        </p>
      </div>
      <img className={styles.GameWordTimer} src={timerRank} alt="GameTemplate" />
    </div>
  );
};

export default GameRankTimer;
