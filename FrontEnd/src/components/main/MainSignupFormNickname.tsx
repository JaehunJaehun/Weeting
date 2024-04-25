import styles from '@/styles/main/MainLoginForm.module.css';

const MainSignupFormNickname = ({ nickname, onNicknameHandler }) => {
  const nickNameCheckHandler = (e: React.FormEvent) => {
    e.preventDefault();
    // nickname 중복확인 api call
    console.log('hi');
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
        <button onClick={nickNameCheckHandler}>중복 확인</button>
      </div>
      <div>이미 존재하는 닉네임 입니다</div>
    </>
  );
};

export default MainSignupFormNickname;
