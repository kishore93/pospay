import React, { useState, useEffect } from "react";

const CountdownTimer = ({ initialTime }) => {
  const [timeLeft, setTimeLeft] = useState(initialTime);

  useEffect(() => {
    if (timeLeft <= 0) return;

    const timerId = setInterval(() => {
      setTimeLeft((prevTime) => prevTime - 1);
    }, 1000);

    return () => clearInterval(timerId);
  }, [timeLeft]);

  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${minutes.toString().padStart(2, "0")}:${secs
      .toString()
      .padStart(2, "0")}`;
  };

  return (
    <div style={{ textAlign: "center" }}>
      {timeLeft > 0 ? (
        <div>{formatTime(timeLeft)}</div>
      ) : (
        <div>
          <p style={{ alignContent: "center" }}>QR code has expired.</p>
        </div>
      )}
    </div>
  );
};

export default CountdownTimer;
