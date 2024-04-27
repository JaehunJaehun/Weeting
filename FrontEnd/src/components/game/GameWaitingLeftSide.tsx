import styles from '@/styles/game/GameWaiting.module.css';
import React, { useState, useEffect } from 'react';
import { RoomInfo } from '@/types/game';
import GameWaitingReadyButton from '@/components/game/GameWaitingReadyButton';
import GameWaitingQuitButton from '@/components/game/GameWaitingQuitButton';
import GameWaitingMemberList from '@/components/game/GameWaitingMemberList';
import GameWaitingLogo from '@/components/game/GameWaitingLogo';
import GameWordTimer from '@/components/game/GameWordTimer'
import GameTimer from '@/assets/images/timerNormal.png'

const GameWaitingLeftSide = ({ roomInfo }: { roomInfo: RoomInfo }) => {
  const [blink, setBlink] = useState(false);

  useEffect(() => {
    if (roomInfo.roomstatus === 'allready') {
      setBlink(true);
      setTimeout(() => {
        setBlink(false);
      }, 5000); // 5초 후에 반짝이는 효과를 종료
    }
  }, [roomInfo.roomstatus]);
    return (
    <>
    <div className={styles.Align}>
        <GameWaitingLogo/>
        <GameWaitingMemberList roommembers={roomInfo.roommembers}/>
        {(roomInfo.roomstatus === 'waiting' || roomInfo.roomstatus === 'allready') && (
            <div className={styles.ButtonAlign}>
              <GameWaitingReadyButton roommembers={roomInfo.roommembers} blink={blink}/>
              <GameWaitingQuitButton roomid={roomInfo.roomid}/>
            </div>
        )}
        {roomInfo.roomstatus === 'wordsetting' && (
          <GameWordTimer/>
        )}
        {roomInfo.roomstatus === 'start' && (
          <GameTimer/>
        )}
    </div>
    </>
  );
};

export default GameWaitingLeftSide;
