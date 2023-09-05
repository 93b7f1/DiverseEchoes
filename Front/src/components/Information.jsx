import React, { useState } from "react";

function IconWithInfo({ info }) {
  const [showInfo, setShowInfo] = useState(false);

  const handleMouseOver = () => setShowInfo(true);
  const handleMouseLeave = () => setShowInfo(false);

  return (
    <div style={{ display: "inline-block", position: "relative" }}>
      <div
        style={{
          backgroundColor: "#111111",
          borderRadius: "50%",
          height: "20px",
          width: "20px",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
        onMouseOver={handleMouseOver}
        onMouseLeave={handleMouseLeave}
      >
        <span style={{ fontSize: "15px", color: "white", cursor: "default" }}>
          i
        </span>
      </div>
      {showInfo && (
        <div
          style={{
            position: "absolute",
            bottom: "40px",
            left: "-50%",
            backgroundColor: "#111111",
            padding: "20px",
            borderRadius: "5px",
            boxShadow: "0px 8px 16px 0px rgba(0,0,0,0.2)",
            zIndex: 1,
            width:"300px",
            
          }}
        >
          <p  className="texto-i">{info}</p>
        </div>
      )}
    </div>
  );
}
export default IconWithInfo;
