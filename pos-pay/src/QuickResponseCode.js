import { gql, useQuery } from "@apollo/client";
import { useEffect, useState } from "react";
import CountdownTimer from "./CountdownTimer";

const GET_QR_CODE = gql`
  query GenerateQr {
    generateQr(
      payeeInfo: {
        pa: "********"
        mc: "********"
        pn: "********"
        cu: "********"
      }
    ) {
      data
      expiresAt
    }
  }
`;

const QuickResponseCode = () => {
  const { loading, error, data } = useQuery(GET_QR_CODE);
  const [qrCode, setQrCode] = useState("");
  const [timeLeft, setTimeLeft] = useState(0);

  useEffect(() => {
    if (data?.generateQr) {
      const { data: qrData, expiresAt } = data.generateQr;

      if (qrData) {
        setQrCode(qrData);
      }

      if (expiresAt) {
        const remainingTime = Math.max(0, expiresAt - Date.now());
        setTimeLeft(remainingTime);
      }
    }
  }, [data]);

  if (loading) return <div style={{ textAlign: "center" }}>Loading...</div>;
  if (error)
    return (
      <div style={{ textAlign: "center" }}>
        <p>Error: Failed to initiate payment</p>
      </div>
    );

  return (
    <div>
      {qrCode ? (
        <img
          src={`data:image/png;base64,${qrCode}`}
          alt="Payment QR Code"
          style={{ width: "300px", height: "auto" }}
        />
      ) : (
        <p>Failed to load Payment QR code.</p>
      )}
      {timeLeft > 0 ? (
        <CountdownTimer initialTime={Math.floor(timeLeft / 1000)} />
      ) : (
        <p style={{ alignContent: "center" }}>QR code has expired.</p>
      )}
    </div>
  );
};

export default QuickResponseCode;
