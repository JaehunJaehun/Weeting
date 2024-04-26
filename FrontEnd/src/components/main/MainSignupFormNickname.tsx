import styles from '@/styles/main/MainLoginForm.module.css';
import { MainSignupFormNicknameProps } from '@/types/user';
import { nicknameCheckApi } from '@/services/userApi';

const MainSignupFormNickname = ({ nickname, onNicknameHandler, nicknamePossible, nicknameCheckHandler }: MainSignupFormNicknameProps) => {
  
  const handleNickNameCheck = (e: React.FormEvent) => {
    e.preventDefault();
    // nickname 중복확인 api call
    nicknameCheckApi({
      nickname: nickname,
    })
      .then((data) => {
        console.log('data :', data);
        if (data.dataBody === true) {
          alert('이미 사용중인 닉네임입니다');
          nicknameCheckHandler(0); // 사용 불가능한 id에 대해 0을 전달
        } else {
          alert('사용할 수 있는 닉네임입니다');
          nicknameCheckHandler(1); // 사용 가능한 id에 대해 1을 전달
        }
      })
      .catch(() => {
        alert('다시 시도해주세요');
      });
    console.log('닉네임 중복확인 api call');
  };

  return (
    <>
      <div className={styles.Mgb}>
        <label className={styles.Label} htmlFor="nickname">
          nickname
        </label>
        <input
          className={styles.InputBox}
          id="nickname"
          type="text"
          placeholder="닉네임을 입력하세요"
          value={nickname}
          onChange={onNicknameHandler}
        />
        <button onClick={handleNickNameCheck} className={styles.checkBtn}>중복 확인</button>
      </div>
      <div className={styles.Container}>
        <div className={styles.Label}></div>
        {nicknamePossible === 0 && (
          <div className={styles.Mgl}>이미 존재하는 닉네임 입니다</div>
        )}
      </div>
    </>
  );
};

export default MainSignupFormNickname;
